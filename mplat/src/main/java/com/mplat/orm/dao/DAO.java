/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mplat.orm.dao;

/**
 * @author Kitty
 */
public class DAO {

    /* 用户DAO */
    private static UserDAO _userDAO;

    public static UserDAO getUserDAO() {
        return _userDAO;
    }

    /* 依赖注入 */
    public void setUserDAO(UserDAO userDAO) {
        _userDAO = userDAO;
    }
}
