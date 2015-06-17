package com.pool3.service;

import java.sql.Connection;
import java.sql.SQLException;

/**
 * Created by alexandr on 17.06.15.
 */
public interface MyDriverManager {

    public Connection getConnection() throws SQLException;

}
