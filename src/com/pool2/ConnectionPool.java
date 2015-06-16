package com.pool2;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Vector;

/**
 * Created by Саша on 16.06.2015.
 * {@link http://habrahabr.ru/post/229199/}
 * ******************************
 * Простейший Connection pool без DataSource в Java
 * - В Java EE Connection Pool реализуется используя Data Source (реализации в Apache Tomcat).
 * - Что делать, если используем только Java SE и нужно организовать многопоточный доступ к базе данных по схеме Connection Pool, ведь сервера приложений в данном случае нет (следовательно, использовать Data Source не можем, а для создания соединений к бд нам придется скорее всего использовать java.sql.DriverManager) ?
 * ************************************************************
 * Хранение деревьев в базе данных. Часть первая, теоретическая
 * {@link http://habrahabr.ru/post/193166/}
 */
public class ConnectionPool {
    // Первый будет содержать список доступных для использования соединений:
    private Vector<Connection> availableConns = new Vector<Connection>();
    // Второй список используемых в любой момент времени:
    private Vector<Connection> usedConns = new Vector<Connection>();
    // Кроме этого определяется переменная url, которая будет хранить строку подключения к бд:
    private String url;

    // В конструкторе используется функция getConnection, которая просто создает новое подключение:
    public ConnectionPool(String url, String driver, int initConnCnt) {
        try {
            Class.forName(driver);
        } catch (Exception e) {
            e.printStackTrace();
        }
        this.url = url;
        for (int i = 0; i < initConnCnt; i++) {
            availableConns.addElement(getConnection());
        }
    }

    private Connection getConnection() {
        Connection conn = null;
        try {
            conn = DriverManager.getConnection(url);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return conn;
    }

    // Эта функция забирает из availableConns очередной Connection и добавляет его в usedConns, затем возвращает это соединение, тем самым он становится используемым:
    public synchronized Connection retrieve() throws SQLException {
        // Сначала мы проверяем, есть ли свободные соединения, если нет, то мы создаем новое подключение, если есть, то мы извлекаем последний элемент из availableConns и удаляем его из вектора свободных соединений.
        // Затем мы только что созданное соединение или извлеченное из списка свободных добавляем в список используемых строкой.
        Connection newConn = null;
        if (availableConns.size() == 0) {
            newConn = getConnection();
        } else {
            newConn = (Connection) availableConns.lastElement();
            availableConns.removeElement(newConn);
        }
        usedConns.addElement(newConn);
        // И возвращаем это соединение.
        return newConn;
    }

    // Когда соединение становится не нужным, то мы выполняем обратную операцию (иначе говоря putback):
    public synchronized void putback(Connection c) throws NullPointerException {
        // Конечно же без synchronized не обойтись.
        // Доступ то многопоточный, вдруг двум потокам выделится одно и то же соединение.
        if (c != null) {
            if (usedConns.removeElement(c)) {
                availableConns.addElement(c);
            } else {
                throw new NullPointerException("Connection not in the usedConns array");
            }
        }
    }

    // Дальше можно по необходимости написать кучу всяких дополнительных функций, которые предоставляют доступ к дополнительной информации, например, функция для получения количества свободных соединений:
    public int getAvailableConnsCnt() {
        return availableConns.size();
    }
}
