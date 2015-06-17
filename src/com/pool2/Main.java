package com.pool2;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.*;

/**
 * Created by Саша on 16.06.2015.
 * ******************************
 * {@link http://www.quizful.net/post/java_enums}
 * {@link http://ithq.ru/index.php/article/view/sortirovka-massiva-i-arraylist-v-java}
 * {@link http://info.javarush.ru/translation/2014/06/14/Как-правильно-делать-сортировку-в-Java.html}
 * {@link https://ru.wikibooks.org/wiki/Реализации_алгоритмов/Сортировка/Пузырьком}
 * {@link http://study-java.ru/uroki-java/urok-11-sortirovka-massiva/}
 * {@link https://docs.oracle.com/javase/tutorial/reflect/member/ctorInstance.html}
 *
 * {@link http://stackoverflow.com/questions/1509391/how-to-get-the-one-entry-from-hashmap-without-iterating}
 * {@link http://stackoverflow.com/questions/1066589/iterate-through-a-hashmap}
 *
 * {@link http://developer.alexanderklimov.ru/android/java/hashmap.php}
 * {@link http://habrahabr.ru/post/128017/}
 * {@link http://info.javarush.ru/translation/2013/10/22/Как-работает-HashMap-в-Java.html}
 *
 * *******************************************************************************************
 * {@link http://habrahabr.ru/post/139736/}
 * {@link http://habrahabr.ru/company/golovachcourses/blog/217595/}
 * {@link http://devcolibri.com/1253}
 * {@link http://www.javenue.info/post/79}
 * {@link http://easy-code.ru/lesson/java-annotations}
 * {@link http://study-and-dev.com/blog/java_annotations_example_1/}
 * {@link http://info.javarush.ru/translation/2014/12/15/Создание-своих-аннотации-в-Java.html}
 * {@link http://www.programru.com/blog/MEzMyADMwIT1.html}
 */
public class Main {

//    Integer a = new Integer("1");
//    Integer[] b = new Integer[10];
    private static List<Book>[]            books;
    private static Map<String, List<Book>> cashe;
    private static Connection[]             conn;
    private static ConnectionPool       connPool;

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



        books    = new ArrayList[10];
        cashe    = new HashMap<>();
        conn     = new Connection[10];
        connPool = ConnectionPool.getInstance("jdbc:mysql://localhost/javalessons_jdbc", "com.mysql.jdbc.Driver", 10, "root", "1978");

        try {
            System.out.println( "Available Connection = " + connPool.getAvailableConnsCnt() );

            Thread[] thread = new Thread[10];
            for (int i = 0; i < 5; i++){
                conn[i] = connPool.retrieve();
                thread[i] = new Thread(new MyRunnable(conn[i], connPool));
            }

            for (int i = 0; i < 5; i++)
                thread[i].start();

            System.out.println( "Available Connection = " + connPool.getAvailableConnsCnt() );
        } catch (SQLException sqle){ System.err.println("Err: SQLException"); }
    }

    public static void bookPrint(List<Book> books){
        for(Book book : books)
            System.out.println(book.toString());
        System.out.println();
    }

    public static List<Book> getSort(List<Book> books, BookSort sort){
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
        return books;
    }

    static class MyRunnable implements Runnable {

        private Connection conn;
        private ConnectionPool connPool;

        public MyRunnable(){}
        public MyRunnable(Connection conn, ConnectionPool connPool){
            this.conn = conn;
            this.connPool = connPool;
        }

        @Override
        public void run() {
            try {
                StatementCashe st = new StatementCashe(conn);

                List<Book> books = st.executeQuery("SELECT * FROM books");
                bookPrint(books);

                st.close();
                connPool.putback(conn);
            } catch (SQLException sqle){ System.err.println("Err: SQLException"); }
        }
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