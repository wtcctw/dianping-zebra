package com.dianping.zebra.monitor.spring;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

import org.junit.Test;

import com.dianping.zebra.monitor.model.DataSourceInfo;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

public class UploadMonitorInfoTest {

	private Type listType = new TypeToken<ArrayList<DataSourceInfo>>() {
   }.getType();
   
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
	
	@Test
	public void testRevertGson(){
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
		
		Gson gson = new Gson();
		List<DataSourceInfo> dsInfos = gson.fromJson(gson.toJson(infos), listType);
	
		for(DataSourceInfo dsInfo : dsInfos){
			System.out.println(dsInfo.getApp() + ":" + dsInfo.getIp() + ":" + dsInfo.getDatabase());
		}
	}
}
