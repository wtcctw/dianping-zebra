/**
 * Project: com.dianping.zebra.zebra-client-0.1.0
 * 
 * File Created at 2011-6-21
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
package com.dianping.zebra.shard.jdbc.util;

import java.util.*;

/**
 * 
 * @author danson.liu
 * 
 */
@SuppressWarnings({ "rawtypes", "unchecked" })
public class CollectionUtils {

	private static Integer	INTEGER_ONE	= Integer.valueOf(1);

	public static Set intersection(final Set a, final Set b) {
		Set set = new LinkedHashSet();
		Map mapa = getCardinalityMap(a);
		Map mapb = getCardinalityMap(b);
		Set elts = new LinkedHashSet(a);
		elts.addAll(b);
		Iterator it = elts.iterator();
		while (it.hasNext()) {
			Object obj = it.next();
			for (int i = 0, m = Math.min(getFreq(obj, mapa), getFreq(obj, mapb)); i < m; i++) {
				set.add(obj);
			}
		}
		return set;
	}

	public static Map getCardinalityMap(final Collection coll) {
		Map count = new HashMap();
		for (Iterator it = coll.iterator(); it.hasNext();) {
			Object obj = it.next();
			Integer c = (Integer) (count.get(obj));
			if (c == null) {
				count.put(obj, INTEGER_ONE);
			} else {
				count.put(obj, Integer.valueOf(c.intValue() + 1));
			}
		}
		return count;
	}

	private static final int getFreq(final Object obj, final Map freqMap) {
		Integer count = (Integer) freqMap.get(obj);
		if (count != null) {
			return count.intValue();
		}
		return 0;
	}

}
