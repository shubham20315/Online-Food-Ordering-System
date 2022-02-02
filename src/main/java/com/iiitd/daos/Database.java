package com.iiitd.daos;

import java.sql.*;

public class Database {
    String dbName;
    String url;

    private static Database db;
    Connection conn;

    Database (){
        dbName = "food_ordering.db";
        url = "jdbc:sqlite:databases/" + dbName;
        createNewDatabase();
    }

    public static Database getInstance(){
        if(db == null){
            db = new Database();
        }
        return db;
    }

    public void createNewDatabase() {
        try {
            conn = DriverManager.getConnection(url);
            if (conn != null) {
                DatabaseMetaData meta = conn.getMetaData();
                /*System.out.println("The driver name is " + meta.getDriverName());
                System.out.println("The driver version is " + meta.getDatabaseProductVersion());
                System.out.println("A new database has been created.");*/
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }

    public boolean createUsersTable() {

        String sql = "CREATE TABLE IF NOT EXISTS users " +
                "(id integer PRIMARY KEY AUTOINCREMENT, first_name text NOT NULL, last_name text NOT NULL," +
                "email text NOT NULL, password text NOT NULL, state text NOT NULL, city text NOT NULL, " +
                "pincode text NOT NULL, address text NOT NULL, latitude decimal(10,8) NOT NULL, longitude decimal(11,8) NOT NULL, " +
                "created_at DATETIME DEFAULT CURRENT_TIMESTAMP);";

        try {
            Statement stmt = conn.createStatement();
            stmt.execute(sql);
            //System.out.println("A new table has been created.");
            return true;
        } catch (SQLException e) {
            System.out.println(e.getMessage());
            return false;
        }
    }

    public boolean createRestaurantsTable(){
        String sql="CREATE TABLE IF NOT EXISTS restaurants " +
                "(id integer PRIMARY KEY AUTOINCREMENT, name text NOT NULL, state text NOT NULL," +
                "city text NOT NULL, pincode text NOT NULL, address text NOT NULL);";
        try {
            Statement stmt = conn.createStatement();
            stmt.execute(sql);
            //System.out.println("A new table has been created.");
            return true;
        } catch (SQLException e) {
            //System.out.println(e.getMessage());
            return false;
        }
    }
    public boolean createMenuTable() {
        String sql="CREATE TABLE IF NOT EXISTS menu " +
                "(id integer PRIMARY KEY AUTOINCREMENT, restaurant_id integer NOT NULL," +
                "item_name text NOT NULL, item_price DOUBLE NOT NULL);";
        try {
            Statement stmt = conn.createStatement();
            stmt.execute(sql);
            return true;
        }
        catch(SQLException e){
            //System.out.println(e.getMessage());
            return false;
        }
    }

    public boolean createCartTable() {
        String sql="CREATE TABLE IF NOT EXISTS cart " +
                "(id integer PRIMARY KEY AUTOINCREMENT, user_id integer NOT NULL," +
                "restaurant_id integer NOT NULL, item_id integer NOT NULL, quantity integer NOT NULL," +
                "created_at DATETIME DEFAULT CURRENT_TIMESTAMP);";
        try {
            Statement stmt = conn.createStatement();
            stmt.execute(sql);
            return true;
        }
        catch(SQLException e){
            //System.out.println(e.getMessage());
            return false;
        }
    }

    public boolean createPromotionsTable() {
        String sql="CREATE TABLE IF NOT EXISTS promotions " +
                "(id integer PRIMARY KEY AUTOINCREMENT, user_id integer NOT NULL," +
                "order_id integer NOT NULL, promo_code integer NOT NULL," +
                "created_at DATETIME DEFAULT CURRENT_TIMESTAMP);";
        try {
            //Connection conn = DriverManager.getConnection(url);
            Statement stmt = conn.createStatement();
            stmt.execute(sql);
            return true;
        }
        catch(SQLException e){
            //System.out.println(e.getMessage());
            return false;
        }
    }

    public boolean createOrdersTable() {
        String sql="CREATE TABLE IF NOT EXISTS orders " +
                "(id integer PRIMARY KEY AUTOINCREMENT, user_id integer NOT NULL," +
                "restaurant_id integer NOT NULL," +
                "amount_paid DOUBLE NOT NULL, discount DOUBLE NOT NULL, status varchar(20) NOT NULL, " +
                "created_at DATETIME DEFAULT CURRENT_TIMESTAMP);";
        try {
            //Connection conn = DriverManager.getConnection(url);
            Statement stmt = conn.createStatement();
            stmt.execute(sql);
            return true;
        }
        catch(SQLException e){
            System.out.println(e.getMessage());
            return false;
        }
    }

    public boolean createOrderItemsTable() {
        String sql="CREATE TABLE IF NOT EXISTS order_items " +
                "(id integer PRIMARY KEY AUTOINCREMENT, order_id integer NOT NULL," +
                "item_id integer NOT NULL, quantity integer NOT NULL, price DOUBLE NOT NULL" +
                "created_at DATETIME DEFAULT CURRENT_TIMESTAMP);";
        try {
            //Connection conn = DriverManager.getConnection(url);
            Statement stmt = conn.createStatement();
            stmt.execute(sql);
            return true;
        }
        catch(SQLException e){
            //System.out.println(e.getMessage());
            return false;
        }
    }

    public boolean createRatingsTable() {
        String sql="CREATE TABLE IF NOT EXISTS ratings " +
                "(id integer PRIMARY KEY AUTOINCREMENT, user_id integer NOT NULL," +
                "app integer NOT NULL, food integer NOT NULL," +
                "created_at DATETIME DEFAULT CURRENT_TIMESTAMP);";
        try {
            //Connection conn = DriverManager.getConnection(url);
            Statement stmt = conn.createStatement();
            stmt.execute(sql);
            return true;
        }
        catch(SQLException e){
            //System.out.println(e.getMessage());
            return false;
        }
    }

    public void insert(String dbName, String name, double age) {
        // SQLite connection string
        // SQL statement for inserting an entry
        String sql = "INSERT INTO employees(name, age) VALUES(?,?)";

        try {
            //Connection conn = DriverManager.getConnection(url);
            PreparedStatement pstmt = conn.prepareStatement(sql);
            pstmt.setString(1, name);
            pstmt.setDouble(2, age);
            pstmt.executeUpdate();
            System.out.println("A new entry has been created.");
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }

    public void selectAll(String dbName) {
        // SQLite connection string
        String url = "jdbc:sqlite:" + dbName;
        // SQL statement for selecting each entry
        String sql = "SELECT * FROM employees";

        try {
            //Connection conn = DriverManager.getConnection(url);
            Statement stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery(sql);

            // loop through the result set
            while (rs.next()) {
                System.out.println(rs.getInt("id") + "\t" + rs.getString("name") + "\t" + rs.getDouble("age"));
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }

    public ResultSet execute(String query){
        ResultSet rs = null;
        try{
            //conn = DriverManager.getConnection(db.url);
            Statement stmt = conn.createStatement();
            rs = stmt.executeQuery(query);


        } catch (Exception e){
            System.out.println(e.getMessage());
        }
        return rs;
    }
}
