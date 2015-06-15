package com.pool;

import org.postgresql.ds.PGSimpleDataSource;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.TimeUnit;

/**
 * Created by Саша on 13.06.2015.
 */
public class DBPoolCacheMy extends DBPool {
    private String url, user, password;
    private PGSimpleDataSource source;
    private BlockingQueue<Connection> connections = new ArrayBlockingQueue<Connection>(20);

//    DBPoolCacheMy(String host, String database, String user, String password) throws SQLException {
    DBPoolCacheMy(String host, String database, String user, String password) throws ClassNotFoundException, SQLException {
        super("jdbc:postgresql://" + host + ":5432/" + database, user, password);
        source = new PGSimpleDataSource();
        source.setServerName(host);
        source.setDatabaseName(database);
        source.setUser(user);
        source.setPassword(password);
        for (int i = 0; i < 20; i++) {//Подготавливаем соединения
            connections.add(new MyConnection(source.getConnection()));
        }
    }

    public Connection getConnection() throws SQLException {
        try {  // пробуем получить свободное соединение
            return connections.poll(2, TimeUnit.SECONDS);
        } catch (InterruptedException e) {
            return null;
        }
    }

    public void putConnection(Connection connection) throws SQLException {
        connections.add(connection);
    }
}
