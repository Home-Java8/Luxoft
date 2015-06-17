package com.pool3;

import com.pool3.service.FactoryDriverManager;
import com.pool3.service.MyDriverManager;
import com.pool3.service.TypeConnection;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

/**
 * Created by alexandr on 17.06.15.
 * ********************************
 *
 * Например пусть мы будем иметь ранее неизвестное приложение (утилита, сервлет, используем hibernate, ...), которые должны иметь коннект к базе.
 * И в соответствии с типом нашего приложения, способы для подключения к базе могут разные использоваться (либо DriverManager, либо DataSource, либо persistence, ...)
 * Чтобы привести весь этот механизм к единому решению можно применить паттерн "фабричный метод" - конечная реализация объекта екземпляра класса будет известная
 * на момент выполнения...
 *
 * {@link http://helios.cs.ifmo.ru/~ad/Education_Information/Comp_Based_Inf_Systems/Practic_5/DataSource.html}
 * {@link http://habrahabr.ru/post/101342/}
 * {@link }
 */
public class Main {

    public static void main(String[] args) {
        try {
            Class.forName("com.mysql.jdbc.Driver");
        }catch (ClassNotFoundException cnfe){ System.err.println("ERR: ClassNotFoundException"); }

        try{
            MyDriverManager driverManager = FactoryDriverManager.choose(TypeConnection.POOL, "jdbc:mysql://localhost/bookstore", "root", "1111");
//            Connection conn = DriverManager.getConnection("jdbc:mysql://localhost/bookstore", "root", "1111");
            Connection conn = driverManager.getConnection();

            Statement st = conn.createStatement();
            ResultSet rs = st.executeQuery("SELECT * FROM books");
            while (rs.next())
                System.out.println("> " + rs.getInt("id") + ", " + rs.getString("title"));
        }catch (SQLException sqle){ System.err.println("ERR: SQLException"); }
    }

}
