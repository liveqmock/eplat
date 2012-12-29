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
import java.util.ArrayList;
import java.util.List;

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
            if (rs.next()) {
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

    public List<UserInfoDTO> seleteAll() {
        List<UserInfoDTO> users = new ArrayList<UserInfoDTO>();

        Connection conn = null;
        PreparedStatement pstmt = null;
        ResultSet rs = null;
        try {
            String sql = SQLUtils.selectUserInfoAllSQL();
            conn = this.jdbcMgt.fetchConnection();
            pstmt = conn.prepareStatement(sql);
            rs = pstmt.executeQuery();
            while (rs.next()) {
                UserInfoDTO user = new UserInfoDTO();
                user.setUserName(rs.getString(DBFields.USER_INFO_USER_NAME));
                user.setPassword(rs.getString(DBFields.USERA_INFO_USER_PASSWD));
                
                users.add(user);
            }
        } catch (Exception e) {
            LogUtils.error("查询所有用户异常.", e);
        } finally {
            JdbcUtils.close(rs);
            JdbcUtils.close(pstmt);
            this.jdbcMgt.freeConnection(conn);
        }

        return users;
    }

    /**
     * 新增用户
     */
    public boolean create(UserInfoDTO user) {
        Connection conn = null;
        PreparedStatement pstmt = null;
        try {
            String sql = SQLUtils.insertUserInfoSQL();
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
        Connection conn = null;
        PreparedStatement pstmt = null;
        try {
            String sql = SQLUtils.updateUserInfoSQL();
            conn = this.jdbcMgt.fetchConnection();
            pstmt = conn.prepareStatement(sql);
            pstmt.setString(1, user.getUserName());
            pstmt.setString(2, user.getPassword());

            pstmt.execute();
            return true;
        } catch (Exception e) {
            LogUtils.error("更新用户异常, User[" + user + "].", e);
        } finally {
            JdbcUtils.close(pstmt);
            this.jdbcMgt.freeConnection(conn);
        }

        return false;
    }

    /**
     * 删除用户
     */
    public boolean delete(String userName) {
        Connection conn = null;
        PreparedStatement pstmt = null;
        try {
            String sql = SQLUtils.deleteUserInfoSQL();
            conn = this.jdbcMgt.fetchConnection();
            pstmt = conn.prepareStatement(sql);
            pstmt.setString(1, userName);

            pstmt.execute();
            return true;
        } catch (Exception e) {
            LogUtils.error("删除用户异常, UserName[" + userName + "].", e);
        } finally {
            JdbcUtils.close(pstmt);
            this.jdbcMgt.freeConnection(conn);
        }

        return false;
    }

    public JdbcMgt getJdbcMgt() {
        return jdbcMgt;
    }

    public void setJdbcMgt(JdbcMgt jdbcMgt) {
        this.jdbcMgt = jdbcMgt;
    }
}
