package com.dianping.zebra.admin.controller;

import java.io.IOException;
import java.util.Map;
import java.util.Set;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.HashSet;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.dianping.zebra.biz.service.LionService;

import jodd.util.StringUtil;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration({ "classpath*:config/spring/local/appcontext-*.xml",
		"classpath*:config/spring/common/appcontext-*.xml" })
public class SearchNoVipDbs {

	@Autowired
	private LionService lionService;

	@Test
	public void Test() throws IOException {
		Map<String, String> jdbcRefWithDbs = lionService.getConfigByProject("product", "groupds");
		Map<String, String> allDs = lionService.getConfigByProject("product", "ds");

		for (Map.Entry<String, String> entry : jdbcRefWithDbs.entrySet()) {
			Set<String> readList = new HashSet<String>();
			Set<String> writeList = new HashSet<String>();

			String dsString[] = entry.getValue().split(",");
			if (dsString.length > 1) {
				for (String ds : dsString) {
					Pattern makeDsRule = Pattern.compile("[\\w+\\-]+");
					Matcher dsRule = makeDsRule.matcher(ds);
					if (dsRule.find()) {
						ds = ds.substring(dsRule.start(), dsRule.end());
					} else {
						break;
					}

					String makeDsUrl = "ds." + ds + ".jdbc.url";
					String dsUrl = allDs.get(makeDsUrl);
					if(StringUtil.isBlank(dsUrl)) {
						continue;
					}
					Pattern makeUrlRule = Pattern.compile("[\\d+\\.]+");
					Matcher urlRule = makeUrlRule.matcher(dsUrl);
					if (urlRule.find()) {
						String host = dsUrl.substring(urlRule.start(), urlRule.end());
						if (ds.indexOf("-read") > 0) {
							readList.add(host);
						} else if (ds.indexOf("-write") > 0) {
							writeList.add(host);
						}
					} else {
						System.out.println("dbname:" + ds + "jdbcurl错误 jdbcurl:" + dsUrl);
					}
				}
				
				int end = entry.getKey().indexOf(".mapping");
				if(end < 8) {
					continue;
				}
				String jdbcRef = entry.getKey().substring(8,end);
				
				if(jdbcRef.indexOf(".sdw") > 0) {
					continue;
				}

				if (writeList.size() <= 0 && readList.size() <= 0) {

				} else if (writeList.size() <= 0) {
					System.out.println("jdbcRef:" + jdbcRef + ",没有写库!");
				} else if (readList.size() <= 0) {
					System.out.println("jdbcRef:" + jdbcRef + ",没有读库!");
				} else if (writeList.size() > 1) {
					System.out.println("jdbcRef:" + jdbcRef + ",只能有一个写库!");
				}

				for (String host : writeList) {
					if (!readList.contains(host)) {
						System.out.println("jdbcRef:" + jdbcRef + ",未配置vip!");
					}
				}
			}
		}
	}
}
