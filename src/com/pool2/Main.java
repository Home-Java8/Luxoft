package com.pool2;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

/**
 * Created by Саша on 16.06.2015.
 * ******************************
 * {@link http://www.quizful.net/post/java_enums}
 * {@link http://ithq.ru/index.php/article/view/sortirovka-massiva-i-arraylist-v-java}
 * {@link http://info.javarush.ru/translation/2014/06/14/Как-правильно-делать-сортировку-в-Java.html}
 * {@link https://ru.wikibooks.org/wiki/Реализации_алгоритмов/Сортировка/Пузырьком}
 * {@link http://study-java.ru/uroki-java/urok-11-sortirovka-massiva/}
 * {@link https://docs.oracle.com/javase/tutorial/reflect/member/ctorInstance.html}
 */
public class Main {

    public static void main(String[] args) {
//        try{
//            Class.forName("com.mysql.jdbc.Driver");
//        } catch (ClassNotFoundException cnfe){ System.err.println("Err: ClassNotFoundException"); }
//
//        try{
//            Connection conn = DriverManager.getConnection("jdbc:mysql://localhost/bookstore", "root", "1111");
//            Statement st = conn.createStatement();
//            ResultSet rs = st.executeQuery("SELECT * FROM books");
//
//            System.out.println( rs.getMetaData().getColumnCount() );
//
//            while (rs.next())
//                System.out.println( "> " + rs.getInt("id") + " " + rs.getString("title") );
//            st.close();
//            conn.close();
//        } catch (SQLException sqle){ System.err.println("Err: SQLException"); }


        List<Book> books1 = new ArrayList<Book>();
        List<Book> books2 = new ArrayList<Book>();
        ConnectionPool connPool = ConnectionPool.getInstance("jdbc:mysql://localhost/bookstore", "com.mysql.jdbc.Driver", 10, "root", "1111");
        try {
            System.out.println( "Available Connection = " + connPool.getAvailableConnsCnt() );
            Connection conn1 = connPool.retrieve();
            Connection conn2 = connPool.retrieve();
            Connection conn3 = connPool.retrieve();
            System.out.println( "Available Connection = " + connPool.getAvailableConnsCnt() );

            Statement st1 = conn1.createStatement();
            ResultSet rs1 = st1.executeQuery("SELECT * FROM books");
            while (rs1.next())
                books1.add(new Book(rs1.getInt("id"), rs1.getString("title"), rs1.getString("comment"), rs1.getDouble("price"), rs1.getString("author")));
            st1.close();
            connPool.putback(conn1);
            bookPrint(books1, BookSort.AUTHOR);

            System.out.println();
            Statement st2 = conn2.createStatement();
            ResultSet rs2 = st2.executeQuery("SELECT * FROM books");
            while (rs2.next())
                books2.add( new Book(rs2.getInt("id"), rs2.getString("title"), rs2.getString("comment"), rs2.getDouble("price"), rs2.getString("author")) );
            st2.close();
            connPool.putback(conn2);
            bookPrint(books2);

            System.out.println( "Available Connection = " + connPool.getAvailableConnsCnt() );
        } catch (SQLException sqle){ System.err.println("Err: SQLException"); }

//        Collections.sort(books1, new Comparator<Book>() {
//            @Override
//            public int compare(Book book1, Book book2) {
//                if(book1.getAuthor().toString().length() == book2.getAuthor().toString().length())
//                    return 0;
//                else if(book1.getAuthor().toString().length() > book2.getAuthor().toString().length())
//                    return 1;
//                else
//                    return -1;
//            }
//        });
//
//        for(Book book : books1)
//            System.out.println(book.toString());
    }


    public static void bookPrint(List<Book> books, BookSort sort){
        Collections.sort(books, new Comparator<Book>() {
            @Override
            public int compare(Book book1, Book book2) {
                if (BookSort.TITLE == sort.TITLE) {
                    if (book1.getTitle().toString().length() == book2.getTitle().toString().length())
                        return 0;
                    else if (book1.getTitle().toString().length() > book2.getTitle().toString().length())
                        return 1;
                    else
                        return -1;
                } else if(BookSort.COMMENT == sort.COMMENT) {
                    if(book1.getComment().toString().length() == book2.getComment().toString().length())
                        return 0;
                    else if(book1.getComment().toString().length() > book2.getComment().toString().length())
                        return 1;
                    else
                        return -1;
                } else if(BookSort.PRICE == sort.PRICE) {
                    if(book1.getPrice() == book2.getPrice())
                        return 0;
                    else if(book1.getPrice() > book2.getPrice())
                        return 1;
                    else
                        return -1;
                } else if(BookSort.AUTHOR == sort.AUTHOR) {
                    if(book1.getAuthor().toString().length() == book2.getAuthor().toString().length())
                        return 0;
                    else if(book1.getAuthor().toString().length() > book2.getAuthor().toString().length())
                        return 1;
                    else
                        return -1;
                } else {
                    if(book1.getId() == book2.getId())
                        return 0;
                    else if(book1.getId() > book2.getId())
                        return 1;
                    else
                        return -1;
                }
            }
        });
        for(Book book : books)
            System.out.println(book.toString());
    }

    public static void bookPrint(List<Book> books){
        Collections.sort(books, new Comparator<Book>() {
            @Override
            public int compare(Book book1, Book book2) {
                if(book1.getId() == book2.getId())
                    return 0;
                else if(book1.getId() > book2.getId())
                    return 1;
                else
                    return -1;
            }
        });

        for(Book book : books)
            System.out.println(book.toString());
    }

}

enum BookSort {
    ID, TITLE, COMMENT, PRICE, AUTHOR;
}


class Book {
//class Book implements Comparator<Book> {

    private int id;
    private String title;
    private String comment;
    private Double price;
    private String author;

    public Book(int id, String title, String comment, Double price, String author){
        this.id = id;
        this.title = title;
        this.comment = comment;
        this.price = price;
        this.author = author;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }

    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

//    @Override
//    public int compare(Book book1, Book book2) {
//        if(book1.author.toString().length() == book2.author.toString().length())
//            return 0;
//        else if(book1.author.toString().length() > book2.author.toString().length())
//            return 1;
//        else
//            return -1;
//    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Book)) return false;

        Book book = (Book) o;

        if (id != book.id) return false;
        if (author != null ? !author.equals(book.author) : book.author != null) return false;
        if (comment != null ? !comment.equals(book.comment) : book.comment != null) return false;
        if (price != null ? !price.equals(book.price) : book.price != null) return false;
        if (title != null ? !title.equals(book.title) : book.title != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = id;
        result = 31 * result + (title != null ? title.hashCode() : 0);
        result = 31 * result + (comment != null ? comment.hashCode() : 0);
        result = 31 * result + (price != null ? price.hashCode() : 0);
        result = 31 * result + (author != null ? author.hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        return "Book{" +
                "id=" + id +
                ", title='" + title + '\'' +
                ", comment='" + comment + '\'' +
                ", price=" + price +
                ", author='" + author + '\'' +
                '}';
    }
}