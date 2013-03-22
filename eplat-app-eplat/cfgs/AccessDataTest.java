/**
 * Alipay.com Inc.
 * Copyright (c) 2004-2013 All Rights Reserved.
 */
package com.alipay.mbill.jdbc;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;

/**
 * AccessÊý¾Ý²âÊÔ
 * 
 * @author shizihu
 * @version $Id: AccessDataTest.java, v 0.1 2013-3-22 ÏÂÎç01:29:08 shizihu Exp $
 */
public class AccessDataTest {

    /**
     * @param args
     */
    public static void main(String[] args) throws Exception {
        Connection conn = null;
        Statement stmt = null;
        ResultSet rs = null;

        String file = "D:/CodeSVN/trunk/eplat/trunk/eplat-app-eplat/cfgs/TrainManage.mdb";
        String url = "jdbc:odbc:Driver={MicroSoft Access Driver (*.mdb)};DBQ=" + file;
        String sql = "SELECT * FROM ALSKnowledgeGlobalThemes";

        try {
            Class.forName("sun.jdbc.odbc.JdbcOdbcDriver").newInstance();
            conn = DriverManager.getConnection(url, "", "");
            stmt = conn.createStatement();
            rs = stmt.executeQuery(sql);

            while (rs.next()) {
                System.out.println(rs.getString("theme_solution"));
            }
        } finally {
            DBUtils.closeQuietly(rs);
            DBUtils.closeQuietly(stmt);
            DBUtils.closeQuietly(conn);
        }
    }
}
