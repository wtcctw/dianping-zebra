package com.dianping.zebra.biz.service.impl;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.URL;
import java.net.URLConnection;

import com.dianping.zebra.biz.service.HttpService;

import org.springframework.stereotype.Service;
import org.unidal.helper.Files;
import org.unidal.helper.Urls;

import com.dianping.cat.Cat;
import com.dianping.cat.message.Message;
import com.dianping.cat.message.Transaction;

@Service
public class HttpServiceImpl implements HttpService {

	@Override
	public String sendGet(String url) {
		Transaction transaction = Cat.newTransaction("Http", "Get");

		InputStream inputStream = null;
		try {
			Cat.logEvent("Url", url);
			inputStream = Urls.forIO().connectTimeout(1000).readTimeout(5000).openStream(url);
			transaction.setStatus(Message.SUCCESS);

			return Files.forIO().readFrom(inputStream, "utf-8");
		} catch (IOException e) {
			Cat.logError(e);
			transaction.setStatus(e);
			return "";
		} finally {
			if (inputStream != null) {
				try {
					inputStream.close();
				} catch (IOException e) {
				}
			}

			transaction.complete();
		}
	}

	@Override
	public String sendPost(String url, String params) {
		Transaction transaction = Cat.newTransaction("Http", "Post");

		PrintWriter out = null;
		BufferedReader in = null;
		String result = "";
		try {
			Cat.logEvent("Url", url);
			URL realUrl = new URL(url);
			// 打开和URL之间的连接
			URLConnection conn = realUrl.openConnection();
			// 设置通用的请求属性
			conn.setRequestProperty("accept", "*/*");
			conn.setRequestProperty("connection", "Keep-Alive");
			conn.setRequestProperty("user-agent", "Mozilla/4.0 (compatible; MSIE 6.0; Windows NT 5.1; SV1)");

			// 发送POST请求必须设置如下两行
			conn.setConnectTimeout(2000);
			conn.setReadTimeout(10000);
			conn.setDoOutput(true);
			conn.setDoInput(true);
			// 获取URLConnection对象对应的输出流
			out = new PrintWriter(conn.getOutputStream());
			// 发送请求参数

			// String encodedParams = URLEncoder.encode(params,"utf-8");
			out.print(params);
			// flush输出流的缓冲
			out.flush();

			// 定义BufferedReader输入流来读取URL的响应
			in = new BufferedReader(new InputStreamReader(conn.getInputStream()));
			String line;

			boolean isFirst = true;
			while ((line = in.readLine()) != null) {
				if (isFirst) {
					result += line;
					isFirst = false;
				} else {
					result += "\n" + line;
				}
			}

			transaction.setStatus(Message.SUCCESS);
		} catch (Exception e) {
			transaction.setStatus(e);
			Cat.logError(e);
		}
		// 使用finally块来关闭输出流、输入流
		finally {
			try {
				if (out != null) {
					out.close();
				}
				if (in != null) {
					in.close();
				}
			} catch (IOException ex) {
				ex.printStackTrace();
			}

			transaction.complete();
		}

		return result;
	}
}
