package com.iiitd.daos;

import com.iiitd.models.Cart;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class CartDao implements Dao<Cart> {

    private static CartDao cartDao = null;

    private CartDao() {
        db.createCartTable();
    }

    public static CartDao getInstance() {
        if (cartDao == null) {
            cartDao = new CartDao();
        }
        return cartDao;
    }

    @Override
    public Cart find(int id) {
        return null;
    }

    @Override
    public List getAll() {
        return null;
    }

    @Override
    public Boolean store(Cart cart) {

        String sql = "INSERT INTO cart" +
                "(user_id, restaurant_id, item_id, quantity) " +
                "VALUES(?,?,?,?)";

        try {

            //db.conn = DriverManager.getConnection(db.url);
            PreparedStatement pstmt = db.conn.prepareStatement(sql);

            pstmt.setInt(1, cart.getUserId());
            pstmt.setInt(2, cart.getRestaurantId());
            pstmt.setInt(3, cart.getItemId());
            pstmt.setInt(4, cart.getQuantity());
            pstmt.executeUpdate();

            return true;
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return false;

    }

    public List get(int userId) {
        String sql = "SELECT cart.*, menu.price, menu.name FROM cart JOIN menu ON menu.id=cart.item_id WHERE user_id='"+ userId +"'";
        List<Cart> cart = new ArrayList<>();
        try {
            ResultSet rs = db.execute(sql);

            while (rs.next()) {
                Cart item = new Cart(rs.getInt("id"), rs.getInt("user_id"), rs.getInt("restaurant_id"),
                        rs.getInt("item_id"), rs.getInt("quantity"));
                item.setItemTotal(rs.getDouble("price") * rs.getInt("quantity"));
                item.setItemName(rs.getString("name"));
                cart.add(item);
            }

        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return cart;
    }

    public Boolean checkRestaurant(int userId, int restaurantId){
        String sql = "SELECT * FROM cart WHERE user_id='"+ userId +"'";
        try {
            ResultSet rs = db.execute(sql);

            while (rs.next()) {
                if(rs.getInt("restaurant_id") == restaurantId){
                    return true;
                } else{
                    return false;
                }
            }

        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return true;

    }

    public Boolean deleteAll(int userId){
        String sql = "DELETE FROM cart WHERE user_id=?";
        try {
            //Connection conn = DriverManager.getConnection(db.url);
            PreparedStatement pstmt = db.conn.prepareStatement(sql);
            pstmt.setInt(1, userId);
            pstmt.executeUpdate();
            return true;

        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return false;

    }

    public Boolean deleteItem(int cartId){
        String sql = "DELETE FROM cart WHERE id=?";
        try {
            //Connection conn = DriverManager.getConnection(db.url);
            PreparedStatement pstmt = db.conn.prepareStatement(sql);
            pstmt.setInt(1, cartId);
            pstmt.executeUpdate();
            return true;

        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return false;

    }
}
