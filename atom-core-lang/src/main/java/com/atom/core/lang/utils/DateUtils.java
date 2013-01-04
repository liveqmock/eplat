/**
 * aBoy.com Inc.
 * Copyright (c) 2004-2012 All Rights Reserved.
 */
package com.atom.core.lang.utils;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

import org.apache.commons.lang.StringUtils;

/**
 * 日期时间工具类
 * 
 * @author obullxl@gmail.com
 * @version $Id: DateUtils.java, 2012-9-12 下午10:03:27 Exp $
 */
public class DateUtils extends org.apache.commons.lang.time.DateUtils {
    /** 日期格式：yyyyMMdd */
    public static final String DS = "yyyyMMdd";

    /** 日期格式：yyyy-MM-dd */
    public static final String DW = "yyyy-MM-dd";

    /** 日期格式：yyyy-MM-dd HH:mm:ss */
    public static final String DL = "yyyy-MM-dd HH:mm:ss";

    /**
     * 尝试解析时间。
     * <p/>
     * yyyyMMdd -> yyyy-MM-dd -> yyyy-MM-dd HH:mm:ss
     */
    public static final Date tryParseDate(String dateValue) {
        if (StringUtils.isBlank(dateValue)) {
            return null;
        }

        Date value = null;
        try {
            value = toDateDS(dateValue);
            if (value == null) {
                value = toDateDW(dateValue);

                if (value == null) {
                    value = toDateDL(dateValue);
                }
            }
        } catch (Exception e) {
            // ignore
        }

        return value;
    }

    /**
     * 解析日期：yyyyMMdd
     */
    public static final Date toDateDS(String dateValue) {
        return toDate(dateValue, DS);
    }

    /**
     * 解析日期：yyyy-MM-dd
     */
    public static final Date toDateDW(String dateValue) {
        return toDate(dateValue, DW);
    }

    /**
     * 解析日期：yyyy-MM-dd HH:mm:ss
     */
    public static final Date toDateDL(String dateValue) {
        return toDate(dateValue, DL);
    }

    /**
     * 解析日期
     */
    public static final Date toDate(String dateValue, String format) {
        Date value = null;
        try {
            value = newDateFormat(format).parse(dateValue);
        } catch (Exception e) {
            // ignore
        }

        return value;
    }

    /**
     * 格式化
     */
    public static final String toString(Date date, String format) {
        String value = null;
        try {
            value = newDateFormat(format).format(date);
        } catch (Exception e) {
            // ignore
        }

        return value;
    }

    /**
     * 格式化：yyyyMMdd
     */
    public static final String toStringDS(Date date) {
        return toString(date, DS);
    }

    /**
     * 格式化：yyyy-MM-dd
     */
    public static final String toStringDW(Date date) {
        return toString(date, DW);
    }

    /**
     * 格式化：yyyy-MM-dd HH:mm:ss
     */
    public static final String toStringDL(Date date) {
        return toString(date, DL);
    }

    /**
     * 日期格式化器
     */
    public static final DateFormat newDateFormat(String format) {
        return new SimpleDateFormat(format, Locale.getDefault());
    }

    /**
     * 日期格式化器
     */
    public static final DateFormat newDateFormat(String format, Locale locale) {
        return new SimpleDateFormat(format, locale);
    }

}
