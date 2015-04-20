package com.dianping.zebra.monitor.spring;

import java.util.ArrayList;
import java.util.List;

import com.dianping.zebra.monitor.model.DataSourceInfo;

public class UploadMonitorInfoTest {

	private static String url = "http://127.0.0.1:8080/a/notify";

	public static void main(String[] args) {
		DataSourceAutoReplacer replacer = new DataSourceAutoReplacer();

		List<DataSourceInfo> infos = new ArrayList<DataSourceInfo>();
		DataSourceInfo info = new DataSourceInfo();
		info.setApp("bc-tuangou-server");
		info.setIp("192.168.211.58");
		info.setDatabase("tgreceipt");
		info.setDataSourceBeanName("bcTuangouReceiptNewReadonlyDataSourceInAccountBiz");
		info.setMaxPoolSize("1");
		info.setUrl("jdbc:mysql://10.1.77.20:3306/TGReceipt?characterEncoding=UTF8");
		info.setDataSourceBeanClass("com.mchange.v2.c3p0.ComboPooledDataSource");

		DataSourceInfo info1 = new DataSourceInfo();
		info1.setApp("bc-tuangou-server");
		info1.setIp("192.168.211.58");
		info1.setDatabase("tuangou2010");
		info1.setMaxPoolSize("1");

		infos.add(info);
		infos.add(info1);

		replacer.uploadDataSourceInfo(url, infos);
	}
}
