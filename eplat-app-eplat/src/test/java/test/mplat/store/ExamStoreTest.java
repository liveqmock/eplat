/**
 * Author: obullxl@gmail.com
 * Copyright (c) 2004-2013 All Rights Reserved.
 */
package test.mplat.store;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;

import org.apache.commons.lang.StringUtils;

import mplat.mgt.dto.ExamInfoDTO;
import mplat.store.ExamStore;

import com.atom.core.lang.utils.DBUtils;
import com.atom.core.xstream.store.StoreFactory;

/**
 * ExamStore测试和初始化
 * 
 * @author obullxl@gmail.com
 * @version $Id: ExamStoreTest.java, V1.0.1 2013-3-22 下午8:30:23 $
 */
public final class ExamStoreTest {

    /**
     * @param args
     */
    public static void main(String[] args) throws Exception {
        // 初始化
        initData();
    }

    /**
     * 初始化
     */
    protected static void initData() throws Exception {
        Connection conn = null;
        Statement stmt = null;
        ResultSet rs = null;

        String file = "e:/CodeSVN/eplat/eplat-app-eplat/cfgs/TrainManage.mdb";
        String url = "jdbc:odbc:Driver={MicroSoft Access Driver (*.mdb)};DBQ=" + file;
        String sql = "SELECT * FROM ALSKnowledgeGlobalThemes";

        ExamStore store = StoreFactory.get().findStore(ExamStore.class);

        try {
            Class.forName("sun.jdbc.odbc.JdbcOdbcDriver").newInstance();
            conn = DriverManager.getConnection(url, "", "");
            stmt = conn.createStatement();
            rs = stmt.executeQuery(sql);

            while (rs.next()) {
                ExamInfoDTO dto = new ExamInfoDTO();
                dto.setTitle(StringUtils.trimToEmpty(new String(rs.getBytes("theme_content"), "GBK")));
                dto.setRgtNo(StringUtils.trimToEmpty(new String(rs.getBytes("theme_solution"), "GBK")));
                dto.setItemA(StringUtils.trimToEmpty(new String(rs.getBytes("sel_item1"), "GBK")));
                dto.setItemB(StringUtils.trimToEmpty(new String(rs.getBytes("sel_item2"), "GBK")));
                dto.setItemC(StringUtils.trimToEmpty(new String(rs.getBytes("sel_item3"), "GBK")));
                dto.setItemD(StringUtils.trimToEmpty(new String(rs.getBytes("sel_item4"), "GBK")));
                
                // System.out.println(dto);
                store.create(dto);
            }
        } finally {
            DBUtils.closeQuietly(rs);
            DBUtils.closeQuietly(stmt);
            DBUtils.closeQuietly(conn);
        }
    }
    
}
