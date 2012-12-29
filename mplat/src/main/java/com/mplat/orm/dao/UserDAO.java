/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mplat.orm.dao;

import com.mplat.jdbc.JdbcMgt;
import com.mplat.orm.dto.UserInfoDTO;
import java.util.List;

/**
 * @author Kitty
 */
public abstract class UserDAO {

    /**
     * JDBC管理器
     */
    public abstract JdbcMgt getJdbcMgt();
    
    /**
     * 是否存在用户
     */
    public abstract UserInfoDTO select(String userName);
    
    /**
     * 查询所有用户
     */
    public abstract List<UserInfoDTO> seleteAll();

    /**
     * 新增用户
     */
    public abstract boolean create(UserInfoDTO user);

    /**
     * 更新用户
     */
    public abstract boolean update(UserInfoDTO user);

    /**
     * 删除用户
     */
    public abstract boolean delete(String userName);
}
