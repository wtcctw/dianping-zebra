/**
 * Project: ${zebra-syncserver.aid}
 * 
 * File Created at 2011-7-28
 * $Id$
 * 
 * Copyright 2010 dianping.com.
 * All rights reserved.
 *
 * This software is the confidential and proprietary information of
 * Dianping Company. ("Confidential Information").  You shall not
 * disclose such Confidential Information and shall use it only in
 * accordance with the terms of the license agreement you entered into
 * with dianping.com.
 */
package com.dianping.zebra.syncserver.bootstrap;

import java.io.BufferedWriter;
import java.io.OutputStreamWriter;
import java.net.InetAddress;
import java.net.Socket;

/**
 * <p>
 * 用于发送同步Server关闭命令
 * </p>
 * 
 * <p>
 * 使用方法：java com.dianping.zebra.syncserver.bootstrap.StopServer
 * port［同步Server的监听服务端口］
 * </p>
 * 
 * @author Leo Liang
 * 
 */
public class StopServer {

	/**
	 * @param args
	 */
	public static void main(String[] args) throws Exception {
		if (args == null || args.length != 1) {
			System.out.println("Usage: java " + StopServer.class.getName() + " port");
			System.exit(1);
		}
		Socket s = new Socket(InetAddress.getLocalHost(), Integer.parseInt(args[0]));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(s.getOutputStream()));
		bw.write("shutdown");
		bw.newLine();
		bw.flush();
		s.close();
	}

}
