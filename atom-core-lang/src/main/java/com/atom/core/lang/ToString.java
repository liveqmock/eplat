/**
 * aBoy.com Inc.
 * Copyright (c) 2004-2012 All Rights Reserved.
 */
package com.atom.core.lang;

import java.io.Serializable;

import org.apache.commons.lang.builder.ToStringBuilder;

/**
 * ToString基类
 * 
 * @author obullxl@gmail.com
 * @version $Id: ToString.java, 2012-3-18 下午3:12:35 Exp $
 */
public abstract class ToString implements Serializable {
    private static final long serialVersionUID = -3797438786246701491L;

    /** 
     * @see java.lang.Object#toString()
     */
    public String toString() {
        return ToStringBuilder.reflectionToString(this, ToStringStyle.STYLE);
    }

}
