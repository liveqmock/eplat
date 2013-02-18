/**
 * Alipay.com Inc.
 * Copyright (c) 2004-2012 All Rights Reserved.
 */
package com.eplat.unicom.utils;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;

import javax.sql.DataSource;

import org.apache.commons.dbcp.BasicDataSource;

/**
 * DB工具类
 * 
 * @author shizihu
 * @version $Id: DBUtils.java, v 0.1 2012-6-8 下午03:24:24 shizihu Exp $
 */
public class DBUtils {
    /** 当前线程打开的连接 */
    private static final ThreadLocal<Connection> CONN = new ThreadLocal<Connection>();

    /** 数据源 */
    private static final DataSource              DS   = initDataSource();

    private static final DataSource initDataSource() {
        BasicDataSource ds = new BasicDataSource();

        ds.setDriverClassName("com.mysql.jdbc.Driver");
        ds.setUrl("jdbc:mysql://127.0.0.1:3306/test?useUnicode=true&amp;characterEncoding=UTF8");
        ds.setUsername("root");
        ds.setPassword("manager");

        return ds;
    }

    /**
     * 获取数据库连接
     */
    public static final Connection fetchConnection() {
        Connection conn = CONN.get();

        if (conn == null) {
            try {
                conn = DS.getConnection();
            } catch (Exception e) {
                throw new RuntimeException("从数据源中取出连接异常！", e);
            }

            CONN.set(conn);
        }

        return conn;
    }

    /**
     * 释放数据库连接
     */
    public static final void freeConnection() {
        Connection conn = CONN.get();

        if (conn != null) {
            try {
                conn.close();
            } catch (Exception e) {
                throw new RuntimeException("释放数据库连接异常！", e);
            }
        }
    }

    /**
     * 关闭数据源
     */
    public static final void closeDataSource() {
        if (BasicDataSource.class.isAssignableFrom(DS.getClass())) {
            try {
                ((BasicDataSource) DS).close();
            } catch (Exception e) {
                throw new RuntimeException("关闭数据源异常！", e);
            }
        }
    }

    /**
     * 关闭Statement
     */
    public static final void closeQuietly(Statement stmt) {
        try {
            if (stmt != null) {
                stmt.close();
            }
        } catch (Exception e) {
            // ignore
        }
    }

    /**
     * 关闭ResultSet
     */
    public static final void closeQuietly(ResultSet rs) {
        try {
            if (rs != null) {
                rs.close();
            }
        } catch (Exception e) {
            // ignore
        }
    }

}
