package com.pool2;

import java.sql.*;

/**
 * @author Aleksandr Konstantinovitch
 * @version 1.0
 * @date 21/01/2015
 * {@link http://javatalks.ru/topics/7147}
 * {@link http://www.tutorialspoint.com/jdbc/jdbc-drop-database.htm}
 * {@link http://www.tutorialspoint.com/jdbc/updating-result-sets.htm}
 * {@link http://www.quizful.net/post/using-jdbc}
 * {@link http://devcolibri.com/477}
 * {@link http://javaxblog.ru/article/java-jdbc-1/}
 * {@link http://www.mkyong.com/jdbc/jdbc-preparestatement-example-update-a-record/}
 */
public class SelectTable {

    public static Connection         dbConnection = null;
    public static Statement             statement = null;
    private static final String         DB_DRIVER = "com.mysql.jdbc.Driver";
    private static final String     DB_CONNECTION = "jdbc:mysql://localhost:3306/bookstore?characterEncoding=utf8";
    private static final String           DB_USER = "root";
    private static final String       DB_PASSWORD = "1111";
    // Так мы делаем выборку из таблицы в базе:
    private final static String SELECT_TABLE_QUERY = "SELECT * FROM books";


    public static void main(String[] argv) {
        try {
            selectTable();
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }

    private static void selectTable() throws SQLException {
        try {
            dbConnection = dbConnection();
            /**
             * Класс 'Statement' - представляет встроенную команду SQL для доступа к базе данных.
             * При закрытии Statement автоматически закрываются все связанные с ним открытые объекты ResultSet.
             * *********************************************************************
             * public boolean execute(String sql) throws SQLException        // позволяет вам выполнить Statement, когда неизвестно заранее, является SQL-строка запросом или обновлением.
             * public ResultSet executeQuery(String sql) throws SQLException // используется для выполнения запросов на извлечение данных. Он возвращает ResultSet
             * public int executeUpdate(String sql) throws SQLException      // используется для выполнения обновлений. Он возвращает количество обновленных строк.
             * public int[ ] executeBatch(String sql) throws SQLException    // вызов-выполнение хранимой процедуры.
             * public ResultSet getResultSet() throws SQLException           // возвращает текущий ResultSet, для каждого результата его следует вызывать только однажды (его не нужно вызывать после обращения к executeQuery()).
             * public void close() throws SQLException                       // вручную закрывает объект Statement, обычно Statement автоматически закрывается при закрытии Connection (не все разработчики JDBC-драйверов придерживаются этих конвенций).
             * *********************************************************************
             */
            statement = dbConnection.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_UPDATABLE);

            /**
             * Класс 'ResultSet' - представляет результирующий набор базы данных, обеспечивает приложению построчный доступ к результатам запросов в базе данных.
             * Во время обработки запроса ResultSet поддерживает указатель на текущую обрабатываемую строку. Приложение последовательно перемещается по результатам, пока они не будут все обработаны.
             * *********************************************************************
             * public boolean absolute(int row) throws SQLException       // перемещает курсор на заданное число строк от начала, если число положительно, и от конца - если отрицательно.
             * public void afterLast() throws SQLException                // перемещает курсор в конец результирующего набора за последнюю строку.
             * public void beforeFirst() throws SQLException              // перемещает курсор в начало результирующего набора перед первой строкой.
             * public void deleteRow() throws SQLException                // удаляет текущую строку из результирующего набора и базы данных.
             * public ResultSetMetaData getMetaData() throws SQLException // предоставляет объект метаданных для данного ResultSet, содержит информацию о результирующие таблице: количество столбцов, их заголовок и т.д.
             * public int getRow() throws SQLException                    // возвращает номер текущей строки.
             * public boolean next() throws SQLException                  // позволяют переместиться в результирующем наборе на одну строку вперед.
             * public boolean previous() throws SQLException              // позволяют переместиться в результирующем наборе на одну строку назад.
             * *********************************************************************
             */
            // Обратите внимание, что делаем выборку из таблицу с помощью executeQuery().
            ResultSet resultset = statement.executeQuery(SELECT_TABLE_QUERY);

            // получаем информацию из результирующей таблице
            int countColumn = resultset.getMetaData().getColumnCount();
            System.out.println("******************************");
            System.out.println("Count Columns = " + countColumn);
            System.out.println("******************************");

            String author, title, comment;
            int id;
            double price;
            while (resultset.next()) {
                id      = resultset.getInt("id");
                author  = resultset.getString("author");
                title   = resultset.getString("title");
                comment = resultset.getString("comment");
                price   = resultset.getDouble("price");
                System.out.println("******************************");
                System.out.println("ID:      " + id);
                System.out.println("Author:  " + author);
                System.out.println("Title:   " + title);
                System.out.println("Price:   " + price);
                System.out.println("Comment: " + comment);
                System.out.println("******************************");
            }
        } catch (SQLException e) { // Handle errors for JDBC
            System.out.println(e.getMessage());
        } finally {
            if (statement != null) {
                statement.close();
            }
            if (dbConnection != null) {
                dbConnection.close();
            }
        }
    }

    private static Connection dbConnection() {
        /**
         * Класс 'java.sql.Connection' - представляет в JDBC сеанс работы с базой данных (он предоставляет приложению объекты Statement для этого сеанса).
         * По умолчанию каждая команда выполняется в отдельной транзакции.
         * Объект Connection позволяет отключить функцию "Autocommit" (автоматического завершения транзакции) - в этом случае требуется явно завершить транзакцию, иначе результаты выполнения всех команд будут потеряны.
         * *********************************************************************
         * public void close() throws SQLException                   // позволяет вручную освободить все ресурсы, такие как сетевые соединения и блокировки базы данных, связанные с данным объектом Connection.
         * public Statement createStatement() throws SQLException    // создает объект Statement, связанный с сеансом Connection, для которого экземпляры ResultSet имеют тип только для чтения и перемещения в прямом направлении.
         * public boolean getAutoCommit() throws SQLException        // по умолчанию все объекты Connection находятся в режиме автозавершения. В этом режиме каждая команда завершается сразу после выполнения.
         * public void setAutoCommit(boolean ac) throws SQLException // метод setAutoCommit() используется для отключения автозавершения вручную завершить серию команд в приложении как единую транзакцию.
         * public void commit() throws SQLException                  // делает постоянными изменения, произведенные всеми командами, связанными с данным соединением.
         * public DatabaseMetaData getMetaData() throws SQLException // предоставляет сведения относящиеся к базе данных и данному Connection.
         * *********************************************************************
         */
        Connection dbConnection = null;
        try {
            // Загружаем драйвер
            Class.forName(DB_DRIVER);
        } catch (ClassNotFoundException e) {
            System.out.println(e.getMessage());
        } catch (Exception e) { // Handle errors for Class.forName
            e.printStackTrace();
        }

        try {
            // Нужно создать подключение к БД у MySQL есть база 'bookstore', к ней и будем создавать соединение.
            dbConnection = DriverManager.getConnection(DB_CONNECTION, DB_USER, DB_PASSWORD);
            return dbConnection;
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return dbConnection;
    }

}