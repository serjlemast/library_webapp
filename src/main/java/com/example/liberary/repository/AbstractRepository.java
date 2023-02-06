package com.example.liberary.repository;

public abstract class AbstractRepository {

    /**
     * User column
     */
    protected static final String BOOK_ID_COLUMN = "id";
    protected static final String BOOK_NAME_COLUMN = "name";
    protected static final String BOOK_AUTHOR_COLUMN = "author";
    protected static final String BOOK_PUBLISHED_DATE_COLUMN = "publeshed_date";
    protected static final String BOOK_INFO_COLUMN = "info";
    protected static final String BOOK_STATUS_COLUMN = "status";
    protected static final int DEFAULT_COUNT_OF_USERS_ON_PAGE = 2;


    /**
     * User column
     */
    protected static final String USER_ID_COLUMN = "id";
    protected static final String USER_NAME_COLUMN = "username";
    protected static final String USER_PASSWORD_COLUMN = "password";
    protected static final String USER_FIRST_NAME_COLUMN = "first_name";
    protected static final String USER_SECOND_NAME_COLUMN = "second_name";
    protected static final String USER_BLOCKED_COLUMN = "isblocked";

    /**
     * Order column
     */
    protected static final String ORDER_ID_COLUMN = "id";
    protected static final String ORDER_USER_ID_COLUMN = "user_id";
    protected static final String ORDER_LIBRARIAN_ID_COLUMN = "librarian_id";
    protected static final String ORDER_BOOK_ID_COLUMN = "book_id";
    protected static final String ORDER_BROUGHT_DATE_COLUMN = "brought_date";


    /**
     * SQL queries for user table
     */
    public static String SELECT_USERS_SQL_QUERY = "SELECT u.id, first_name, second_name, username," +
            " password, isblocked  FROM users_table AS u LIMIT 2 OFFSET ";
    public static final String SELECT_COUNT_OF_USERS_SQL_QUERY = "SELECT COUNT(id) AS count_id FROM users_table";
    public static String SELECT_USER_BY_ID_SQL_QUERY = "SELECT u.id, first_name, second_name, username, password, isblocked " +
            " FROM users_table AS u WHERE id = ?";

    public static final String SELECT_USER_BY_CREDENTIAL_SQL_QUERY = "SELECT u.id,username,CONCAT(u.first_name,' ', u.second_name) AS full_name,roles_table.name FROM users_table AS u" +
            "    left join roles_table on u.role_id = roles_table.id" +
            "    where username = ? and password = ?";

    public static final String INSERT_NEW_USER_SQL_QUERY = "INSERT INTO users_table (username,password," +
            "first_name,second_name,role_id) VALUES (?,?,?,?,1)";

    public static final String SELECT_LAST_USER_ID_SQL_QUERY = "SELECT MAX(id) AS id FROM users_table";

    public static final String UPDATE_USER_BY_BLOCK_TEMPLATE_SQL_QUERY = "UPDATE users_table SET isblocked = ? WHERE id = ?";
    //todo ROLE
    public static final String UPDATE_USER_TEMPLATE_SQL_QUERY = "UPDATE users_table SET username = ?,password = ?," +
            "first_name = ?,second_name = ?  WHERE id = ?";

    public static final String DELETE_USER_BY_ID_SQL_QUERY = "DELETE FROM users_table WHERE id = ?";


    /**
     * SQL queries for book table
     */
    public static String SELECT_BOOKS_SQL_QUERY = "SELECT * FROM books_table";

    //todo mb Auto increment id
    public static final String INSERT_NEW_BOOK_SQL_QUERY = "INSERT INTO books_table (id,name,author,publeshed_date,info)" +
            " VALUES (?,?,?,?,?)";

    public static final String UPDATE_BOOK_BY_ENABLE_TEMPLATE_SQL_QUERY = "UPDATE books_table SET status  = ? WHERE id = ?";

    public static final String UPDATE_BOOK_TEMPLATE_SQL_QUERY = "UPDATE books_table SET name = ?,author = ?," +
            "publeshed_date = ?,info = ?  WHERE id = ?";

    public static final String DELETE_BOOK_BY_ID_SQL_QUERY = "DELETE FROM books_table WHERE id = ?";
    public static final String SELECT_BOOK_BY_ID_SQL_QUERY = "SELECT b.id, b.name, b.author, b.publeshed_date, b.info, b.status " +
            "FROM books_table AS b WHERE id = ?";

    public static final String SELECT_BOOK_BY_CREDENTIAL_SQL_QUERY = "SELECT b.id, b.name, b.author, b.publeshed_date, b.info, b.status " +
            "FROM books_table AS b " +
            "WHERE b.name LIKE ? " +
            "  and b.author LIKE ? ";


    public static final String INSERT_NEW_ORDER_BY_USER_SQL_QUERY = "insert into orders_table set user_id = 1 , book_id = ?,status = 'CREATED',create_date = current_date;";

    public static String SELECT_ORDERS_BY_CREDENTIALS_SQL_QUERY = "select o.id ,o.create_date, o.status ,o.return_date, o.subscription, o.user_id, " +
            "group_concat(b.name) as book_names, " +
            "group_concat(book_id) as book_ids " +
            "from orders_table as o " +
            "left join order_books_table as obt on o.id = obt.order_id " +
            "left join books_table as b on b.id  = obt.book_id " +
            "where o.status like ? and o.user_id = ?    group by o.id";
    public static String SELECT_ORDERS_SQL_QUERY = "select ot.id ,ot.create_date,ot.return_date, ot.status , ot.subscription, ot.user_id,ot.librarian_id ,group_concat(book_id) as book_ids " +
            "from orders_table as ot " +
            "left join order_books_table as obt on ot.id = obt.order_id " +
            "group by ot.id ";

    public static String SELECT_ORDERS_BY_STATUS_CREATED_QUERY = "select o.id,o.status,o.create_date,u.id,u.username,b.id,b.name,b.status " +
            "from order_books_table as obt " +
            "left join orders_table o on obt.order_id = o.id " +
            "left join users_table u on o.user_id = u.id " +
            "left join books_table b on obt.book_id = b.id " +
            "where o.status = 'CREATED' and u.id = ";

    public static String UPDATE_ORDER_BY_LIBRARIAN = "UPDATE orders_table SET librarian_id = ? , status = ?,subscription = ?,return_date = DATE ? where id = ?";
    public static String UPDATE_ORDER_BY_LIBRARIAN2 = "UPDATE orders_table SET librarian_id = 3 , status = 'CANCELED',subscription = true,return_date = DATE '2022-11-26' where id = 24;";

}
