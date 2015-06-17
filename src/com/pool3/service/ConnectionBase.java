package com.pool3.service;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 * Created by alexandr on 17.06.15.
 */
public class ConnectionBase implements MyDriverManager {

    String url;
    String name;
    String passwd;

    public ConnectionBase(){}
    public ConnectionBase(String url, String name, String passwd){
        this.url = url;
        this.name = name;
        this.passwd = passwd;
    }

    @Override
    public Connection getConnection() throws SQLException {
        return DriverManager.getConnection(url, name, passwd);
    }
}
