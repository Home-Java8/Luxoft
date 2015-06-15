package com.pool;

import org.postgresql.ds.PGPoolingDataSource;

import java.sql.Connection;
import java.sql.SQLException;

/**
 * Created by Саша on 13.06.2015.
 */
public class DBPoolCache extends DBPool {
    private PGPoolingDataSource source;

    DBPoolCache(String host, String database, String user, String password) throws ClassNotFoundException {
        super("jdbc:postgresql://" + host + ":5432/" + database, user, password);
        source = new PGPoolingDataSource();
        source.setDataSourceName("A Data Source");
        source.setServerName(host);
        source.setDatabaseName(database);
        source.setUser(user);
        source.setPassword(password);
        source.setMaxConnections(20);//Максимальное значение
        source.setInitialConnections(20);//Сколько соединений будет сразу открыто
    }

    public Connection getConnection() throws SQLException {
        return source.getConnection();
    }

    public void putConnection(Connection connection) throws SQLException {
        connection.close();
    }
}
