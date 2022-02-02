package com.iiitd.models;

import com.iiitd.daos.OrderItemsDao;

public class OrderItems extends Order{
    private int id;
    private int orderId;
    private int itemId;
    private int quantity;
    private double itemPrice;

    OrderItemsDao orderItemsDao;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getOrderId() {
        return orderId;
    }

    public void setOrderId(int orderId) {
        this.orderId = orderId;
    }

    public int getItemId() {
        return itemId;
    }

    public void setItemId(int itemId) {
        this.itemId = itemId;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public double getItemPrice() {
        return itemPrice;
    }

    public void setItemPrice(double itemPrice) {
        this.itemPrice = itemPrice;
    }

    OrderItems(){
        orderItemsDao = OrderItemsDao.getInstance();
    }

}
