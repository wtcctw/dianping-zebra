/**
 * Project: ${zebra-client.aid}
 * 
 * File Created at 2011-6-17 $Id$
 * 
 * Copyright 2010 dianping.com. All rights reserved.
 * 
 * This software is the confidential and proprietary information of Dianping
 * Company. ("Confidential Information"). You shall not disclose such
 * Confidential Information and shall use it only in accordance with the terms
 * of the license agreement you entered into with dianping.com.
 */
package com.dianping.zebra.jdbc.param;

import java.io.Serializable;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.Arrays;

/**
 * @author Leo Liang
 * 
 */
public abstract class ParamContext implements Serializable {
    private static final long serialVersionUID = -743208754173084268L;
    protected int             index;
    protected Object[]        values;

    public ParamContext(int index, Object[] values) {
        this.index = index;
        if (values != null && values.length != 0) {
            this.values = new Object[values.length];
            for (int i = 0; i < values.length; i++) {
                this.values[i] = values[i];
            }
        }
    }

    /**
     * @return the index
     */
    public int getIndex() {
        return index;
    }

    /**
     * @param index
     *            the index to set
     */
    public void setIndex(int index) {
        this.index = index;
    }

    /**
     * @return the values
     */
    public Object[] getValues() {
        return values;
    }

    /**
     * @param values
     *            the values to set
     */
    public void setValues(Object[] values) {
        if (values != null && values.length != 0) {
            this.values = new Object[values.length];
            for (int i = 0; i < values.length; i++) {
                this.values[i] = values[i];
            }
        }
    }

    public abstract void setParam(PreparedStatement stmt) throws SQLException;

    /*
     * (non-Javadoc)
     * 
     * @see java.lang.Object#toString()
     */
    @Override
    public String toString() {
        return "ParamContext [index=" + index + ", values=" + Arrays.toString(values) + "]";
    }

}
