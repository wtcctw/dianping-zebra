package com.dianping.zebra.admin.admin.page.config;

import java.io.IOException;

import javax.servlet.ServletException;

import jodd.util.URLDecoder;

import org.unidal.lookup.annotation.Inject;
import org.unidal.web.mvc.annotation.InboundActionMeta;
import org.unidal.web.mvc.annotation.OutboundActionMeta;
import org.unidal.web.mvc.annotation.PayloadMeta;

import com.dianping.lion.EnvZooKeeperConfig;
import com.dianping.zebra.admin.admin.page.JsonHandler;
import com.dianping.zebra.admin.admin.service.ConnectionService;
import com.dianping.zebra.admin.admin.service.ConnectionServiceImpl;
import com.dianping.zebra.admin.admin.service.DalConfigService;
import com.dianping.zebra.admin.admin.service.HttpService;
import com.dianping.zebra.admin.admin.service.LionHttpService;
import com.dianping.zebra.group.util.StringUtils;

public class Handler extends JsonHandler<Context> {
	@Inject
	private LionHttpService m_lionHttpService;

	@Inject
	private HttpService m_httpService;

	@Inject
	private ConnectionService m_connectionService;

	@Inject
	private DalConfigService m_dalConfigService;

	@Override
	@PayloadMeta(Payload.class)
	@InboundActionMeta(name = "config")
	public void handleInbound(Context ctx) throws ServletException, IOException {
		// display only, no action here
	}

	@Override
	@OutboundActionMeta(name = "config")
	public void handleOutbound(Context ctx) throws ServletException, IOException {
		try {
			Payload payload = ctx.getPayload();
			Object responseObject = null;
			String currentEnv = EnvZooKeeperConfig.getEnv();

			switch (payload.getAction()) {
			case VIEWDS:
				responseObject = m_dalConfigService.getDsConfig(payload.getEnv(), payload.getKey());
				break;
			case UPDATEDS:
				m_dalConfigService.updateDsConfig(gson.fromJson(URLDecoder.decode(payload.getDsConfigs()),
				      DalConfigService.GroupConfigModel.class));
				break;
			case TEST:
				String jdbcRef = payload.getKey();
				String env = payload.getEnv();
				if (StringUtils.isBlank(env)) {
					env = EnvZooKeeperConfig.getEnv();
				}

				if (jdbcRef.toLowerCase().equals("dpreview")) {
					jdbcRef = "DPReview";
				}

				if (env.equalsIgnoreCase(currentEnv)) {
					ConnectionServiceImpl.ConnectionStatus connectionstatus = new ConnectionServiceImpl.ConnectionStatus();
					ConnectionService.ConnectionResult result = m_connectionService.getConnectionResult(jdbcRef);

					connectionstatus.setConnected(result.isCanConnect());
					connectionstatus.setConfig(result.getConfig().toString());
					responseObject = connectionstatus;
					break;
				} else {
					String host = "";
					if ("alpha".equals(env)) {
						host = "http://192.168.214.228:8080";
					} else if ("qa".equals(env)) {
						host = "http://zebra-web01.beta:8080";
					} else if ("prelease".equals(env)) {
						host = "http://10.2.8.65:8080";
					} else if ("product".equals(env)) {
						host = "http://zebra.dp";
					} else {
						responseObject = "Error: unrecognized lion env!";
					}

					if (host.length() > 0) {
						String url = host + "/a/config?op=test&key=" + jdbcRef + "&env=" + env;
						responseObject = m_httpService.sendGet(url);
						successJson(ctx, (String) responseObject);
					} else {
						success(ctx, responseObject);
					}

					return;
				}
			case ENV:
				if ("qa".equals(currentEnv)) {
					responseObject = m_lionHttpService.getDevEnv();
				} else if ("prelease".equals(currentEnv)) {
					responseObject = new String[] { "prelease" };
				} else if (m_lionHttpService.isDev()) {
					responseObject = new String[] { "dev" };
				} else {
					responseObject = m_lionHttpService.getAllEnv();
				}
				break;
			case CREATE:
				String project = payload.getProject();
				if (project.equals("groupds")) {
					String key = String.format("groupds.%s.mapping", payload.getKey());
					m_lionHttpService.createKey("groupds", key);
					m_lionHttpService.removeUnset(key);
				} else if (project.equals("ds")) {

				}
				break;
			default:
				responseObject = m_lionHttpService.getConfigByProject(payload.getEnv(), "groupds");
				break;
			}

			success(ctx, responseObject);
		} catch (Exception exp) {
			error(ctx, exp.getMessage());
		}
	}

	// private Map<String, String> getConfigByGroupId(String env, String groupId) {
	// Map<String, String> result = new HashMap<String, String>();
	// DalConfigService.GroupConfigModel groupConfig = m_dalConfigService.getDsConfig(env, groupId);
	// result.put(String.format("groupds.%s.mapping", groupId), groupConfig.getConfig());
	// for (DalConfigService.DsConfigModel ds : groupConfig.getConfigs()) {
	// for (DalConfigService.ConfigProperty prop : ds.getProperties()) {
	// result.put(prop.getKey(), prop.getValue());
	// }
	// }
	// return result;
	// }
}
