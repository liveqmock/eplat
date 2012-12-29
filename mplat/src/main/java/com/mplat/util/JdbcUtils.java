/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mplat.util;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;

/**
 * @author Kitty
 */
public class JdbcUtils {

    /**
     * 管理员
     */
    public static final String USER_ADMIN = "admin";
    public static final String USER_PASSWD = "888888";
    /**
     * 数据表名
     */
    public static final String TB_USER_INFO = "adm_user_info";

    /**
     * 关闭数据库Connection
     */
    public static final void close(Connection conn) {
        try {
            if (conn != null) {
                conn.close();
            }
        } catch (Exception e) {
            LogUtils.info("关闭数据库Connection异常!");
        }
    }

    /**
     * 关闭数据库Statement
     */
    public static final void close(Statement stmt) {
        try {
            if (stmt != null) {
                stmt.close();
            }
        } catch (Exception e) {
            LogUtils.info("关闭数据库Statement异常!");
        }
    }

    /**
     * 关闭数据库ResultSet
     */
    public static final void close(ResultSet rs) {
        try {
            if (rs != null) {
                rs.close();
            }
        } catch (Exception e) {
            LogUtils.info("关闭数据库ResultSet异常!");
        }
    }
}
