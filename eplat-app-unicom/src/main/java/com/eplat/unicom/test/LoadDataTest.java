/**
 * Alipay.com Inc.
 * Copyright (c) 2004-2012 All Rights Reserved.
 */
package com.eplat.unicom.test;

import com.eplat.unicom.utils.DBUtils;
import java.sql.Connection;
import java.sql.Statement;

import org.junit.Test;

/**
 * Load数据测试
 * 
 * @author shizihu
 * @version $Id: LoadDataTest.java, v 0.1 2012-6-8 下午03:29:08 shizihu Exp $
 */
public class LoadDataTest {

    /**
     * 完全导入，包括自增字段
     */
    @Test
    public void test_loadData_ALL() throws Exception {
        Connection conn = null;
        Statement stmt = null;
        try {
            conn = DBUtils.fetchConnection();
            stmt = conn.createStatement();

            String sql = "load data infile 'c:/test_key_value.txt' replace into table test_key_value character set GBK fields terminated by ',' enclosed by '\\'' lines terminated by '\\r\\n'";
            boolean result = stmt.execute(sql);

            System.out.println("Load执行结果：" + result);
        } finally {
            DBUtils.freeConnection();
            DBUtils.closeQuietly(stmt);
            DBUtils.closeDataSource();
        }
    }

    /**
     * 部分导入，自增字段自动加1
     */
    @Test
    public void test_loadData_PART() throws Exception {
        Connection conn = null;
        Statement stmt = null;
        try {
            conn = DBUtils.fetchConnection();
            stmt = conn.createStatement();

            String sql = "load data local infile 'c:/test_key_value_02.txt' IGNORE into table test_key_value character set GBK fields terminated by ',' lines terminated by '\\r\\n' (`key`,`value`)";
            boolean result = stmt.execute(sql);

            System.out.println("Load执行结果：" + result);
        } finally {
            DBUtils.freeConnection();
            DBUtils.closeQuietly(stmt);
            DBUtils.closeDataSource();
        }
    }

}
