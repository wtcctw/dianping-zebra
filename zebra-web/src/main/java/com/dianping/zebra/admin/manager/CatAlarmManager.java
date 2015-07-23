package com.dianping.zebra.admin.manager;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.URL;
import java.net.URLConnection;

import com.dianping.zebra.admin.util.CatAlarmContent;

public class CatAlarmManager {

	public static String makeAlarmParam(CatAlarmContent catalarmcontent) {
		catalarmcontent.setOp("insert");
		catalarmcontent.setType("SQL");
		catalarmcontent.setStatus("0");
		
		String domin  = (catalarmcontent.getDomin().split("-"))[0]; 
		if("".equals(domin)) {
			domin = catalarmcontent.getHostname();
		}
		catalarmcontent.setDomin(domin);
		
		return "op=" + catalarmcontent.getOp() + "&type=" + catalarmcontent.getType() + "&title="
		      + catalarmcontent.getTitle() + "&domin=" + catalarmcontent.getDomin() + "&hostname="
		      + catalarmcontent.getHostname() + "&ip=" + catalarmcontent.getIp() + "&user=" + catalarmcontent.getUser()
		      + "&status=" + catalarmcontent.getStatus() + "&alterationDate=" + catalarmcontent.getAlterationDate()
		      + "&content=" + catalarmcontent.getContent();
	}

	public static void sendAlarm(CatAlarmContent catalarmcontent) {
		BufferedReader in = null;
		String param = makeAlarmParam(catalarmcontent);
		try {
			URL url = new URL("http://cat.qa.dianpingoa.com/cat/r/alteration?" + param);
			URLConnection connection = url.openConnection();

			connection.setRequestProperty("Charset", "UTF-8");

			connection.connect(); // 获取所有响应头字段
			// 定义 BufferedReader输入流来读取URL的响应
			in = new BufferedReader(new InputStreamReader(connection.getInputStream()));
		} catch (Exception e) {
		} finally {
			try {
			if (in != null) {
				in.close();
			}
			} catch (Exception e2) {
			}
		}
	}
}