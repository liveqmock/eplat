/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mplat.jdbc;

import java.sql.Connection;

/**
 * @author shizihu
 */
public interface JdbcMgt {

    public void initialize() throws Exception;

    public boolean shutdown();

    public Connection fetchConnection();

    public boolean freeConnection(Connection conn);
}
