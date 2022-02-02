package com.iiitd.daos;

import com.iiitd.models.Order;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

public class OrderDao implements Dao<Order> {

    private static OrderDao orderDao;

    private OrderDao(){
        db.createOrdersTable();
    }

    public static OrderDao getInstance() {
        if (orderDao == null) {
            orderDao = new OrderDao();
        }
        return orderDao;
    }

    @Override
    public Order find(int id) {
        return null;
    }

    public Order findByUser(int userId){
        String sql = "SELECT *, datetime(orders.created_at, 'localtime') AS ordered_at "+
                "FROM orders " +
                "WHERE user_id='"+ userId +"' AND status='pending' " +
                "ORDER BY created_at DESC LIMIT 1";
        Order order = null;
        try {
            ResultSet rs = db.execute(sql);

            while (rs.next()) {
                order = new Order(rs.getInt("id"), rs.getInt("user_id"),
                        rs.getInt("restaurant_id"), rs.getDouble("amount_paid"),
                        rs.getDouble("discount"), rs.getString("ordered_at"));

            }

        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return order;
    }

    @Override
    public List<Order> getAll() {
        return null;
    }

    @Override
    public Boolean store(Order order) {
        String sql = "INSERT INTO orders" +
                "(user_id, restaurant_id, amount_paid, discount, status) " +
                "VALUES(?,?,?,?,?)";

        try {

            PreparedStatement pstmt = db.conn.prepareStatement(sql);

            pstmt.setInt(1, order.getUserId());
            pstmt.setInt(2, order.getRestaurantId());
            pstmt.setDouble(3, order.getAmountPaid());
            pstmt.setDouble(4, order.getDiscount());
            pstmt.setString(5, "pending");
            pstmt.executeUpdate();

            return true;
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return false;
    }

    public Boolean update(int userId){
        String sql = "UPDATE orders " +
                "SET status=? " +
                "WHERE user_id=? AND status=?";

        try {

            PreparedStatement pstmt = db.conn.prepareStatement(sql);

            pstmt.setString(1, "cancelled");
            pstmt.setInt(2, userId);
            pstmt.setString(3, "pending");
            pstmt.executeUpdate();

            return true;
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return false;
    }


}
