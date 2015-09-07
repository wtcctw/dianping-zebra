package com.dianping.zebra.admin.controller;

import java.io.IOException;
import java.lang.reflect.Type;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpMethod;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.client.RestTemplate;

import com.dianping.cat.Cat;
import com.dianping.zebra.biz.dao.MonitorHistoryMapper;
import com.dianping.zebra.biz.dto.InstanceStatusDto;
import com.dianping.zebra.biz.dto.MonitorDto;
import com.dianping.zebra.biz.service.LionService;
import com.dianping.zebra.group.config.DataSourceConfigManager;
import com.dianping.zebra.group.config.DataSourceConfigManagerFactory;
import com.dianping.zebra.group.config.datasource.entity.DataSourceConfig;
import com.dianping.zebra.group.config.datasource.entity.GroupDataSourceConfig;
import com.dianping.zebra.util.StringUtils;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

@Controller
@RequestMapping(value = "/monitor")
public class MonitorController extends BasicController {

	private static final String LION_KEY = "zebra.monitorservice.jdbcreflist";

	private static final String USER_NAME = "zebra.monitorservice.jdbc.username";

	private static final String USER_PASSWD = "zebra.monitorservice.jdbc.password";

	@Autowired
	private LionService lionService;

	@Autowired
	private MonitorHistoryMapper monitorHistoryDao;

	@Autowired
	private RestTemplate restClient;

	private Gson gson = new Gson();

	private Type type = new TypeToken<Map<String, Set<String>>>() {
	}.getType();

	private Type type1 = new TypeToken<Map<String, InstanceStatusDto>>() {
	}.getType();
	
	/*查询敷在最低的监控机器*/
	private String findLowLoadMachine(Map<String, Set<String>> ipWithJdbcRef) {
		String bestIp = null;
		int load = Integer.MAX_VALUE;

		for (Entry<String, Set<String>> entry : ipWithJdbcRef.entrySet()) {
			String ip = entry.getKey();
			Set<String> jdbcRefs = entry.getValue();

			if (load > jdbcRefs.size()) {
				load = jdbcRefs.size();
				bestIp = ip;
			}
		}

		return bestIp;
	}
	
	/*测试账号是否有该jdbcRef所在ip的访问权限*/
	public boolean testConnection(String jdbcRef) {
		String username = lionService.getConfigFromZk(USER_NAME);
		String password = lionService.getConfigFromZk(USER_PASSWD);

		if (StringUtils.isBlank(username) || StringUtils.isBlank(password)) {
			return false;
		}

		DataSourceConfigManager dsCongfigManager = DataSourceConfigManagerFactory.getConfigManager("remote", jdbcRef);
		GroupDataSourceConfig groupDsConfig = dsCongfigManager.getGroupDataSourceConfig();
		dsCongfigManager.close();

		Map<String, DataSourceConfig> dsConfMap = groupDsConfig.getDataSourceConfigs();

		for (Map.Entry<String, DataSourceConfig> entry : dsConfMap.entrySet()) {
			DataSourceConfig dsConfig = entry.getValue();
			if (dsConfig.isActive()) {
				String driverClass = dsConfig.getDriverClass();

				try {
					Class.forName(driverClass);
				} catch (ClassNotFoundException e) {
					Cat.logError(e);
				}

				String jdbcUrl = buildJdbcUrl(dsConfig);

				Connection con = null;

				try {
					con = DriverManager.getConnection(jdbcUrl, username, password);
				} catch (SQLException se) {
					return false;
				} finally {
					close(con);
				}
			} else {
				return false;
			}
		}

		return true;
	}

	/*截取形如xxx:xxx://127.0.0.1:3306 状ip*/
	private String buildJdbcUrl(DataSourceConfig dsConfig) {
		String url = dsConfig.getJdbcUrl();

		Pattern p = Pattern.compile("\\:\\d+");
		Matcher m = p.matcher(url);
		if(m.find()) {
			url = url.substring(0, m.end());
		}
		
		return url;
	}

	/*关闭数据库链接*/
	private void close(Connection con) {
		if (con != null) {
			try {
				con.close();
			} catch (SQLException ingore) {
			}
		}
	}

	/*自动将jdbcRef添加到负载最低的监控机器上*/
	@RequestMapping(value = "/addJdbcRef", method = RequestMethod.GET)
	@ResponseBody
	public Object addJdbcRef(String env, String jdbcRef) throws IOException {
		if (!lionService.getAllEnv().contains(env)) {
			return new MonitorDto(MonitorDto.ErrorStyle.EnvError);
		}

		if (!isRightJdbcRef(env, jdbcRef)) {
			return new MonitorDto(MonitorDto.ErrorStyle.JdbcError);
		}

		// hack for DPReview
		if (!jdbcRef.equalsIgnoreCase("DPReview")) {
			jdbcRef = jdbcRef.toLowerCase();
		}

		Map<String, Set<String>> ipWithJdbcRef = getIpWithJdbcRefByEnv(env);

		// 检查是否有重复
		for (Entry<String, Set<String>> entry : ipWithJdbcRef.entrySet()) {
			Set<String> monitoredJdbcRef = entry.getValue();

			if (monitoredJdbcRef.contains(jdbcRef)) {
				return new MonitorDto(MonitorDto.ErrorStyle.Success);
			}
		}

		if (!testConnection(jdbcRef)) {
			return new MonitorDto(MonitorDto.ErrorStyle.ConnectError);
		}

		String ip = findLowLoadMachine(ipWithJdbcRef);
		Set<String> jdbcRefSet = ipWithJdbcRef.get(ip);

		if (jdbcRefSet == null) {
			jdbcRefSet = new HashSet<String>();
		}
		jdbcRefSet.add(jdbcRef);

		String json = gson.toJson(ipWithJdbcRef);
		lionService.setConfig(env, LION_KEY, json);

		return new MonitorDto(MonitorDto.ErrorStyle.Success);
	}

	/*将该jdbcRef从监控中移除*/
	@RequestMapping(value = "/removeJdbcRef", method = RequestMethod.GET)
	@ResponseBody
	public Object removeJdbcRef(String env, String jdbcRef) throws IOException {
		if (!lionService.getAllEnv().contains(env)) {
			return new MonitorDto(MonitorDto.ErrorStyle.EnvError);
		}

		if (StringUtils.isNotBlank(jdbcRef)) {
			if (!jdbcRef.equalsIgnoreCase("DPReview")) {
				jdbcRef = jdbcRef.toLowerCase();
			}

			Map<String, Set<String>> ipWithJdbcRef = getIpWithJdbcRefByEnv(env);

			if (ipWithJdbcRef == null) {
				return new MonitorDto(MonitorDto.ErrorStyle.Success);
			}

			for (Map.Entry<String, Set<String>> entry : ipWithJdbcRef.entrySet()) {
				Set<String> jdbcRefSet = entry.getValue();
				if (jdbcRefSet != null && jdbcRefSet.contains(jdbcRef)) {
					jdbcRefSet.remove(jdbcRef);
				}
			}

			String json = gson.toJson(ipWithJdbcRef);

			lionService.setConfig(env, LION_KEY, json);

			return new MonitorDto(MonitorDto.ErrorStyle.Success);
		}
		return new MonitorDto(MonitorDto.ErrorStyle.JdbcError);
	}

	@RequestMapping(value = "/submit", method = RequestMethod.GET)
	@ResponseBody
	public Object submitJdbcRef(String ip, String jdbcRefs) throws Exception {
		Map<String, Set<String>> ipWithJdbcRef = getIpWithJdbcRef();

		if (StringUtils.isNotBlank(jdbcRefs)) {
			Set<String> newJdbcRefs = new HashSet<String>();

			if (!jdbcRefs.equalsIgnoreCase("null")) {
				String[] jdbcRefSplits = jdbcRefs.trim().split(",");

				for (String jdbcRef : jdbcRefSplits) {
					if (!jdbcRef.equalsIgnoreCase("DPReview")) {
						jdbcRef = jdbcRef.toLowerCase();
					}
					boolean isMonitored = false;

					// 在其他IP中已经监控的jdbcRef不会再一次被监控
					for (Entry<String, Set<String>> entry : ipWithJdbcRef.entrySet()) {
						Set<String> monitoredJdbcRef = entry.getValue();

						if (!entry.getKey().equals(ip) && monitoredJdbcRef.contains(jdbcRef)) {
							isMonitored = true;
							break;
						}
					}

					if (!testConnection(jdbcRef)) {
						return new MonitorDto(MonitorDto.ErrorStyle.ConnectError);
					}

					if (!isMonitored) {
						newJdbcRefs.add(jdbcRef);
					}
				}
			}

			ipWithJdbcRef.put(ip, newJdbcRefs);

			String json = gson.toJson(ipWithJdbcRef);

			lionService.setConfig(lionService.getEnv(), LION_KEY, json);
		}

		return new MonitorDto(MonitorDto.ErrorStyle.Success);
	}

	/*检测jdbcRef是否存在*/
	public boolean isRightJdbcRef(String env, String jdbcRef) throws IOException {
		if (StringUtils.isBlank(jdbcRef)) {
			return false;
		}

		Set<String> jdbcRefSet = getJdbcRefSet(lionService.getEnv());

		return jdbcRefSet.contains(jdbcRef);
	}

	/*获取指定环境的监控信息*/
	private Map<String, Set<String>> getIpWithJdbcRefByEnv(String env) throws IOException {
		String config = lionService.getConfigByHttp(env, LION_KEY);

		if (config != null) {
			return gson.fromJson(config, type);
		} else {
			return new HashMap<String, Set<String>>();
		}
	}

	private Map<String, Set<String>> getIpWithJdbcRef() {
		String config = lionService.getConfigFromZk(LION_KEY);
		if (config != null) {
			return gson.fromJson(config, type);
		} else {
			return new HashMap<String, Set<String>>();
		}
	}

	/*获取监控机器状态*/
	@RequestMapping(value = "/getStatus", method = RequestMethod.GET)
	@ResponseBody
	public Object getStatus(String ip) throws Exception {
		Map<String, InstanceStatusDto> result = new HashMap<String, InstanceStatusDto>();

		String url = String.format("http://%s:8080/a/status", ip);
		String jsonBody = restClient.exchange(url, HttpMethod.GET, null, String.class).getBody();

		if (jsonBody != null) {
			Map<String, InstanceStatusDto> fromJson = gson.fromJson(jsonBody, type1);

			result.putAll(fromJson);
		}

		return result;
	}

	public Set<String> getJdbcRefSet(String env) throws IOException {
		Set<String> jdbcRefSet = new HashSet<String>();
		HashMap<String, String> dsKV = lionService.getConfigByProject(env, "groupds");

		for (Entry<String, String> entry : dsKV.entrySet()) {
			String key = entry.getKey();
			String value = entry.getValue();

			if (key != null && value != null) {
				int begin = "groupds.".length();
				int end = key.indexOf(".mapping");
				if (end > begin) {
					jdbcRefSet.add(key.substring(begin, end));
				}
			}
		}

		return jdbcRefSet;
	}

	@RequestMapping(value = "/getTickedList", method = RequestMethod.GET)
	@ResponseBody
	public Set<String> getTickedList(String ip) throws Exception {
		if (StringUtils.isBlank(ip)) {
			return null;
		}

		return getIpWithJdbcRef().get(ip);
	}

	@RequestMapping(value = "/getAllMonitored", method = RequestMethod.GET)
	@ResponseBody
	public Set<String> getAllMonitored(String env) throws IOException {
		if (!lionService.getAllEnv().contains(env)) {
			return null;
		}

		Set<String> monitoredList = new HashSet<String>();

		Map<String, Set<String>> ipWithJdbcRef = getIpWithJdbcRefByEnv(env);
		for (Map.Entry<String, Set<String>> entry : ipWithJdbcRef.entrySet()) {
			monitoredList.addAll(entry.getValue());
		}

		return monitoredList;
	}

	@RequestMapping(value = "/getJdbcRefList", method = RequestMethod.GET)
	@ResponseBody
	public Set<String> getJdbcRefList() throws IOException {
		return getJdbcRefSet(lionService.getEnv());
	}

	@RequestMapping(value = "/servers", method = RequestMethod.GET)
	@ResponseBody
	public Object getServers() throws Exception {
		return getIpWithJdbcRef().keySet();
	}

	@RequestMapping(value = "/history", method = RequestMethod.GET)
	@ResponseBody
	public Object showHistory() throws Exception {
		return monitorHistoryDao.findAllHistory();
	}
}
