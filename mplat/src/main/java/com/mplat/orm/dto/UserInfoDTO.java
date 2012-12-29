/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mplat.orm.dto;

/**
 * @author Kitty
 */
public class UserInfoDTO {

    private String userName;
    private String password;

    public String toString() {
        return this.userName;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
