/**
 * Project: ${zebra-client.aid}
 * 
 * File Created at 2011-6-16 $Id$
 * 
 * Copyright 2010 dianping.com. All rights reserved.
 * 
 * This software is the confidential and proprietary information of Dianping
 * Company. ("Confidential Information"). You shall not disclose such
 * Confidential Information and shall use it only in accordance with the terms
 * of the license agreement you entered into with dianping.com.
 */
package com.dianping.zebra.shard.parser.sqlParser;

import org.antlr.runtime.ANTLRStringStream;
import org.antlr.runtime.CharStream;

public class AntlrStringStream extends ANTLRStringStream{
	public AntlrStringStream(String input) {
		super(input);
	}
	@Override
	public int LA(int i) {
		
		if ( i==0 ) { 
			return 0; // undefined 
			} 
			if ( i<0 ) { 
			i++; 
			} 
			if ( (p+i-1) >= n ) { 
			return CharStream.EOF; 
			} 
			return Character.toUpperCase(data[p+i-1]); 
			} 
}