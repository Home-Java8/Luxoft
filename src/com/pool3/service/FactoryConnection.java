package com.pool3.service;

/**
 * Created by alexandr on 17.06.15.
 */
public class FactoryConnection {

    public static MyDriverManager init(TypeConnection type, String url, String name, String passwd){
        MyDriverManager conn = null;

        switch (type){
            case BASE:
                conn = new ConnectionBase(url, name, passwd);
                break;
            case POOL:
                conn  = ConnectionPool.getInstance(url, "com.mysql.jdbc.Driver", 10, name, passwd);
                break;
            case CONTEXT:
                conn = new ConnectionContext();
                break;
        }

        return conn;
    }

}
