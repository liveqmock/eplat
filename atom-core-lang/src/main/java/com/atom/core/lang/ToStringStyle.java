/**
 * aBoy.com Inc.
 * Copyright (c) 2004-2012 All Rights Reserved.
 */
package com.atom.core.lang;

import java.text.SimpleDateFormat;
import java.util.Date;

import org.apache.commons.lang.StringUtils;

/**
 * ToString样式。
 * <p/>
 * 扩展Commons的ToString工具类：<br/>
 * 1、对于null值，不显示；<br/>
 * 2、对于日期值，格式化为‘yyyy-MM-dd HH:mm:ss’形式；<br/>
 * 
 * @author obullxl@gmail.com
 * @version $Id: ToString.java, 2012-3-18 下午3:12:35 Exp $
 */
public final class ToStringStyle extends org.apache.commons.lang.builder.ToStringStyle {
    private static final long         serialVersionUID = -4063924270218289279L;

    /** 单例 */
    public static final ToStringStyle STYLE            = new ToStringStyle();

    /**
     * CTOR
     */
    private ToStringStyle() {
        super();
        this.setUseFieldNames(true);
        this.setDefaultFullDetail(true);
        this.setUseShortClassName(true);
        this.setUseIdentityHashCode(false);
    }

    /**
     * 确保单例
     */
    private Object readResolve() {
        return ToStringStyle.STYLE;
    }

    /** 
     * @see org.apache.commons.lang.builder.ToStringStyle#appendDetail(java.lang.StringBuffer, java.lang.String, java.lang.Object)
     */
    protected void appendDetail(StringBuffer buffer, String fieldName, Object value) {
        if (value instanceof Date) {
            value = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(value);
        }
        buffer.append(value);
    }

    /** 
     * @see org.apache.commons.lang.builder.ToStringStyle#append(java.lang.StringBuffer, java.lang.String, java.lang.Object, java.lang.Boolean)
     */
    public void append(StringBuffer buffer, String fieldName, Object value, Boolean fullDetail) {
        // 如果为NULL，则不显示
        if (StringUtils.isBlank(fieldName) || value == null) {
            return;
        }

        appendFieldStart(buffer, fieldName);
        appendInternal(buffer, fieldName, value, Boolean.TRUE);
        appendFieldEnd(buffer, fieldName);
    }

    /** 
     * @see org.apache.commons.lang.builder.ToStringStyle#appendDetail(java.lang.StringBuffer, java.lang.String, java.util.Collection)
     */
    //    @SuppressWarnings("unchecked")
    //    protected void appendDetail(StringBuffer buffer, String fieldName, Collection coll) {
    //        buffer.append(super.getContentStart());
    //
    //        Iterator iter = coll.iterator();
    //        while (iter.hasNext()) {
    //            buffer.append(ToStringBuilder.reflectionToString(iter.next(), TO_STRING_STYLE));
    //
    //            if (iter.hasNext()) {
    //                super.appendFieldSeparator(buffer);
    //            }
    //        }
    //
    //        buffer.append(super.getContentEnd());
    //    }

    /** 
     * @see org.apache.commons.lang.builder.ToStringStyle#appendDetail(java.lang.StringBuffer, java.lang.String, java.util.Map)
     */
    //    @SuppressWarnings("unchecked")
    //    protected void appendDetail(StringBuffer buffer, String fieldName, Map map) {
    //        buffer.append(super.getArrayStart());
    //
    //        Iterator iter = map.keySet().iterator();
    //        while (iter.hasNext()) {
    //            Object key = iter.next();
    //
    //            this.append(buffer, ObjectUtils.toString(key), map.get(key), Boolean.TRUE);
    //
    //            if (iter.hasNext()) {
    //                super.appendFieldSeparator(buffer);
    //            }
    //        }
    //
    //        buffer.append(super.getArrayEnd());
    //    }

}
