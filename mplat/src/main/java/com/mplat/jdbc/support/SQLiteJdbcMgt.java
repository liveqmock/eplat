/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mplat.jdbc.support;

import com.mplat.jdbc.JdbcMgt;
import com.mplat.util.ConfigUtils;
import com.mplat.util.JdbcUtils;
import com.mplat.util.LogUtils;
import com.mplat.util.SQLUtils;
import java.sql.Connection;
import java.sql.DatabaseMetaData;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import org.apache.commons.io.FilenameUtils;

/**
 * @author shizihu
 */
public class SQLiteJdbcMgt implements JdbcMgt {

    /**
     * 数据库连接
     */
    private static final ThreadLocal<Connection> _conn = new ThreadLocal<Connection>();
    /**
     * DB文件路径
     */
    private String path;
    
    public void initialize() throws Exception {
        Class.forName("org.sqlite.JDBC");
        
        String config = ConfigUtils.findConfigPath();
        this.path = FilenameUtils.normalize(config + "/mplat.db");
        
        _conn.set(this.newConnection());

        // 初始化数据表
        this.initDataTable();

        // 初始化数据值
        this.initDataValue();
    }
    
    public boolean shutdown() {
        Connection conn = _conn.get();
        
        try {
            if (conn != null && !conn.isClosed()) {
                _conn.remove();
                conn.close();
            }
        } catch (Exception e) {
            LogUtils.error("关闭数据连接异常!", e);
            return false;
        }
        
        return true;
    }
    
    public Connection fetchConnection() {
        Connection conn = _conn.get();
        
        try {
            if (conn == null || conn.isClosed()) {
                _conn.set(this.newConnection());
            }
        } catch (Exception e) {
            LogUtils.error("获取数据连接异常!", e);
            throw new RuntimeException("获取数据连接异常!", e);
        }
        
        return _conn.get();
    }
    
    public boolean freeConnection(Connection conn) {
        try {
            if (conn == null || conn.isClosed()) {
                return false;
            }
            
            _conn.set(conn);
        } catch (Exception e) {
            LogUtils.error("释放数据连接异常!", e);
            return false;
        }
        
        return true;
    }
    
    private Connection newConnection() throws Exception {
        return DriverManager.getConnection("jdbc:sqlite:" + this.path);
    }
    
    private void initDataTable() throws Exception {
        Connection conn = this.fetchConnection();
        this.initDataTable(conn, JdbcUtils.TB_USER_INFO);
    }
    
    private void initDataTable(Connection conn, String table) throws Exception {
        DatabaseMetaData meta = conn.getMetaData();
        ResultSet rs = meta.getTables(null, null, table, new String[]{"TABLE"});
        
        try {
            if (!rs.next()) {
                Statement stmt = conn.createStatement();
                try {
                    String sql = SQLUtils.findDDL(table);
                    stmt.execute(sql);
                } finally {
                    JdbcUtils.close(stmt);
                }
            }
        } finally {
            JdbcUtils.close(rs);
        }
    }
    
    private void initDataValue() throws Exception {
        String selectSQL = SQLUtils.selectUserInfoByNameSQL();
        String insertSQL = SQLUtils.insertUserInfoSQL();
        
        Connection conn = this.fetchConnection();
        PreparedStatement pstmtSelect = null;
        PreparedStatement pstmtInsert = null;
        try {
            pstmtSelect = conn.prepareStatement(selectSQL);
            pstmtInsert = conn.prepareStatement(insertSQL);
            
            // 查询
            pstmtSelect.setString(1, JdbcUtils.USER_ADMIN);
            ResultSet rs = null;
            try {
                rs = pstmtSelect.executeQuery();
                if(!rs.next()) {
                    // 插入
                    pstmtInsert.setString(1, JdbcUtils.USER_ADMIN);
                    pstmtInsert.setString(2, JdbcUtils.USER_PASSWD);
                    
                    pstmtInsert.execute();
                }
            } finally {
                JdbcUtils.close(rs);
            }
        } finally {
            JdbcUtils.close(pstmtSelect);
            JdbcUtils.close(pstmtInsert);
        }
    }
}
