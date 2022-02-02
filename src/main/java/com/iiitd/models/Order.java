package com.iiitd.models;

import com.iiitd.daos.OrderDao;
import java.sql.Timestamp;

public class Order {
    private int id;
    int userId;
    int restaurantId;
    double amountPaid;
    double discount;
    String createdAt;

    OrderDao orderDao;

    /*getters and setters*/
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public int getRestaurantId() {
        return restaurantId;
    }

    public void setRestaurantId(int restaurantId) {
        this.restaurantId = restaurantId;
    }

    public double getAmountPaid() {
        return amountPaid;
    }

    public void setAmountPaid(double amountPaid) {
        this.amountPaid = amountPaid;
    }

    public double getDiscount() {
        return discount;
    }

    public void setDiscount(double discount) {
        this.discount = discount;
    }

    public String getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(String createdAt) {
        this.createdAt = createdAt;
    }

    public Order(){
        orderDao = OrderDao.getInstance();
    }

    public Order(int id, int userId, int restaurantId, double amountPaid, double discount, String createdAt){
        this.id = id;
        this.userId = userId;
        this.restaurantId = restaurantId;
        this.amountPaid = amountPaid;
        this.discount = discount;
        this.createdAt = createdAt;

        orderDao = OrderDao.getInstance();
    }

    /**
     * method to store a new order
     */
    public void create(){
        orderDao.store(this);
    }

    /**
     * method to retrieve an order
     */
    public Order get(int userId){
        return orderDao.findByUser(userId);
    }

    /**
     * method to cancel an order
     */
    public Boolean cancel(int userId){
        return orderDao.update(userId);
    }
}
