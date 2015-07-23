package com.dianping.zebra.monitor;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.URL;
import java.net.URLConnection;
import java.text.SimpleDateFormat;
import java.util.Date;

public class CatAlarmManager {

	public static String makeAlarmParam(String hostname,String title,String user,String ip) {
		CatAlarmContent alarmcontent = new CatAlarmContent();
		alarmcontent.setOp("insert");
		alarmcontent.setType("SQL");
		alarmcontent.setStatus("0");
		alarmcontent.setHostname(hostname);
		alarmcontent.setTitle(title);
		alarmcontent.setUser(user);
		alarmcontent.setIp(ip);
		SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		alarmcontent.setAlterationDate(df.format(new Date()));
		String domin  = (alarmcontent.getDomin().split("-"))[0]; 
		if("".equals(domin)) {
			domin = alarmcontent.getHostname();
		}
		alarmcontent.setDomin(domin);
		
		return "op=" + alarmcontent.getOp() + "&type=" + alarmcontent.getType() + "&title="
		      + alarmcontent.getTitle() + "&domin=" + alarmcontent.getDomin() + "&hostname="
		      + alarmcontent.getHostname() + "&ip=" + alarmcontent.getIp() + "&user=" + alarmcontent.getUser()
		      + "&status=" + alarmcontent.getStatus() + "&alterationDate=" + alarmcontent.getAlterationDate()
		      + "&content=" + alarmcontent.getContent();
	}

	public static void sendAlarm(String hostname,String title,String user,String ip) {
		BufferedReader in = null;
		String param = makeAlarmParam(hostname,title,user,ip);
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