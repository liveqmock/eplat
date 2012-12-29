/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mplat.orm.dao.support;

import com.mplat.jdbc.JdbcMgt;
import com.mplat.orm.dao.UserDAO;
import com.mplat.orm.dto.UserInfoDTO;
import com.mplat.util.DBFields;
import com.mplat.util.JdbcUtils;
import com.mplat.util.LogUtils;
import com.mplat.util.SQLUtils;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

/**
 * @author Kitty
 */
public class SQLiteUserDAO extends UserDAO {

    /**
     * JDBC管理器
     */
    private JdbcMgt jdbcMgt;

    /**
     * 是否存在用户
     */
    public UserInfoDTO select(String userName) {
        UserInfoDTO user = null;
        Connection conn = null;
        PreparedStatement pstmt = null;
        ResultSet rs = null;
        try {
            String sql = SQLUtils.selectUserInfoByNameSQL();
            conn = this.jdbcMgt.fetchConnection();
            pstmt = conn.prepareStatement(sql);
            pstmt.setString(1, userName);
            rs = pstmt.executeQuery();
            if(rs.next()) {
                user = new UserInfoDTO();
                user.setUserName(rs.getString(DBFields.USER_INFO_USER_NAME));
                user.setPassword(rs.getString(DBFields.USERA_INFO_USER_PASSWD));
            }
        } catch (Exception e) {
            LogUtils.error("查询用户异常, UserName[" + userName + "].", e);
        } finally {
            JdbcUtils.close(rs);
            JdbcUtils.close(pstmt);
            this.jdbcMgt.freeConnection(conn);
        }

        return user;
    }

    /**
     * 新增用户
     */
    public boolean create(UserInfoDTO user) {
        Connection conn = null;
        PreparedStatement pstmt = null;
        try {
            String sql = SQLUtils.findUserInfoDDL();
            conn = this.jdbcMgt.fetchConnection();
            pstmt = conn.prepareStatement(sql);
            pstmt.setString(1, user.getUserName());
            pstmt.setString(2, user.getPassword());
            
            pstmt.execute();
            return true;
        } catch (Exception e) {
            LogUtils.error("插入用户异常, User[" + user + "].", e);
        } finally {
            JdbcUtils.close(pstmt);
            this.jdbcMgt.freeConnection(conn);
        }
        
        return false;
    }

    /**
     * 更新用户
     */
    public boolean update(UserInfoDTO user) {
        return false;
    }

    /**
     * 删除用户
     */
    public boolean delete(String userName) {
        return false;
    }

    public JdbcMgt getJdbcMgt() {
        return jdbcMgt;
    }

    public void setJdbcMgt(JdbcMgt jdbcMgt) {
        this.jdbcMgt = jdbcMgt;
    }
}
