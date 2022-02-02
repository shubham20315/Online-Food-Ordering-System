package com.iiitd.daos;

import java.util.List;

public class OrderItemsDao implements Dao<OrderItemsDao> {

    private static OrderItemsDao orderItemsDao;

    private OrderItemsDao(){
        db.createOrderItemsTable();
    }

    public static OrderItemsDao getInstance() {
        if (orderItemsDao == null) {
            orderItemsDao = new OrderItemsDao();
        }
        return orderItemsDao;
    }

    @Override
    public OrderItemsDao find(int id) {
        return null;
    }

    @Override
    public List<OrderItemsDao> getAll() {
        return null;
    }

    @Override
    public Boolean store(OrderItemsDao orderItemsDao) {
        return null;
    }
}
