package com.iiitd.daos;

import com.iiitd.models.Security;
import com.iiitd.models.User;

import java.sql.*;
import java.util.List;

public class UserDao implements Dao<User>{

    private static UserDao userDao;

    private UserDao(){
        db.createUsersTable();
    }

    public static UserDao getInstance() {
        if (userDao == null) {
            userDao = new UserDao();
        }
        return userDao;
    }

    @Override
    public User find(int id) {
        return null;
    }

    @Override
    public List<User> getAll() {
        return null;
    }

    @Override
    public Boolean store(User user) {
        String sql = "INSERT INTO users" +
                "(first_name, last_name, email, password, state, city, pincode, address, latitude, longitude) " +
                "VALUES(?,?,?,?,?,?,?,?,?,?)";

        try {

            PreparedStatement pstmt = db.conn.prepareStatement(sql);
            pstmt.setString(1, user.getFirstName());
            pstmt.setString(2, user.getLastName());
            pstmt.setString(3, user.getEmail());
            pstmt.setString(4, Security.hash(user.getPassword()));
            pstmt.setString(5, user.getState());
            pstmt.setString(6, user.getCity());
            pstmt.setString(7, user.getPincode());
            pstmt.setString(8, user.getAddress());
            pstmt.setDouble(9, user.getLatitude());
            pstmt.setDouble(10, user.getLongitude());
            pstmt.executeUpdate();

            return true;
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return false;
    }

    public ResultSet getUserByEmailAndPassword(String email, String password){
        Database db = Database.getInstance();
        String sql = "SELECT * from users where email=? and password=?";
        ResultSet rs = null;
        try {
            PreparedStatement pstmt = db.conn.prepareStatement(sql);
            pstmt.setString(1, email);
            pstmt.setString(2, Security.hash(password));
            rs = pstmt.executeQuery();

            Boolean found = false;


        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return rs;
    }

    public boolean findEmail(String email){

        String sql = "SELECT * from users where email=?";
        try {
            PreparedStatement pstmt = db.conn.prepareStatement(sql);
            pstmt.setString(1, email);
            ResultSet rs = pstmt.executeQuery();

            if (rs.next()) {
                return false;
            }
            return true;

        } catch (SQLException e) {
            System.out.println(e.getMessage());
            return false;
        }
    }
}
