package com.pool2;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by Саша on 17.06.2015.
 */
public class StatementCashe {

    private Statement           statement = null;
    private Map<String, List<Book>> cashe = null;

    public StatementCashe(){}
    public StatementCashe (Connection conn){
        cashe = new HashMap<>();
        try {
            statement = conn.createStatement();
        } catch (SQLException sqle){ System.err.println("ERR: SQLException"); }
    }

    public List<Book> executeQuery(String query) throws SQLException {
        List<Book> book = null;
        if (cashe != null)
            book = cashe.get(query);

        if (book == null){
            ResultSet rs = statement.executeQuery(query);
            book = new ArrayList<Book>();
            while (rs.next())
                book.add(new Book(rs.getInt("id"), rs.getString("title"), rs.getString("comment"), rs.getDouble("price"), rs.getString("author")));
            cashe.put(query, book);
        }

        return book;
    }

    public void close() throws SQLException {
        statement.close();
    }

}
