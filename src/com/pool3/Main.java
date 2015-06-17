package com.pool3;

import com.pool3.service.FactoryConnection;
import com.pool3.service.MyDriverManager;
import com.pool3.service.TypeConnection;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

/**
 * Created by alexandr on 17.06.15.
 */
public class Main {

    public static void main(String[] args) {
        try {
            Class.forName("com.mysql.jdbc.Driver");
        }catch (ClassNotFoundException cnfe){ System.err.println("ERR: ClassNotFoundException"); }

        try{
            MyDriverManager driverManager = FactoryConnection.init(TypeConnection.POOL, "jdbc:mysql://localhost/bookstore", "root", "1111");
//            Connection conn = DriverManager.getConnection("jdbc:mysql://localhost/bookstore", "root", "1111");
            Connection conn = driverManager.getConnection();

            Statement st = conn.createStatement();
            ResultSet rs = st.executeQuery("SELECT * FROM books");
            while (rs.next())
                System.out.println("> " + rs.getInt("id") + ", " + rs.getString("title"));
        }catch (SQLException sqle){ System.err.println("ERR: SQLException"); }
    }

}
