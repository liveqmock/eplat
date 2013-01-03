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

    public static final String findExamInfoDDL() {
        return findDDL(JdbcUtils.TB_EXAM_INFO);
    }

    public static final String findExamItemDDL() {
        return findDDL(JdbcUtils.TB_EXAM_ITEM);
    }

    public static final String selectUserInfoByNameSQL() {
        return findSQL("selectUserInfoByName");
    }

    public static final String selectUserInfoAllSQL() {
        return findSQL("selectUserInfoAll");
    }

    public static final String insertUserInfoSQL() {
        return findSQL("insertUserInfo");
    }

    public static final String updateUserInfoSQL() {
        return findSQL("updateUserInfo");
    }

    public static final String deleteUserInfoSQL() {
        return findSQL("deleteUserInfo");
    }

    public static final String selectExamInfoSQL() {
        return findSQL("selectExamInfo");
    }

    public static final String selectExamItemByExamSQL() {
        return findSQL("selectExamItemByExam");
    }

    public static final String selectExamInfoAllSQL() {
        return findSQL("selectExamInfoAll");
    }

    public static final String insertExamInfoSQL() {
        return findSQL("insertExamInfo");
    }

    public static final String insertExamItemSQL() {
        return findSQL("insertExamItem");
    }

    public static final String updateExamInfoSQL() {
        return findSQL("updateExamInfo");
    }

    public static final String updateExamItemSQL() {
        return findSQL("updateExamItem");
    }

    public static final String deleteExamInfoSQL() {
        return findSQL("deleteExamInfo");
    }

    public static final String deleteExamItemByExamSQL() {
        return findSQL("deleteExamItemByExam");
    }
}
