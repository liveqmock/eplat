/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mplat.util;

/**
 * @author Kitty
 */
public class SQLUtils {

    public static final String findSQL(String method) {
        return ConfigUtils.findValue("SQL-" + method);
    }
    
    public static final String findDDL(String table) {
        return ConfigUtils.findValue("DDL-" + table);
    }
    
    public static final String findUserInfoDDL() {
        return findDDL(JdbcUtils.TB_USER_INFO);
    }
    
    public static final String selectUserInfoByNameSQL() {
        return findSQL("selectUserInfoByName");
    }
    
    public static final String insertUserInfoSQL() {
        return findSQL("insertUserInfo");
    }
    
}
