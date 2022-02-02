package com.iiitd.daos;

import com.iiitd.models.Restaurant;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class RestaurantDao implements Dao<Restaurant>{

    private static RestaurantDao restaurantDao;

    private RestaurantDao(){
        db.createRestaurantsTable();
    }

    public static RestaurantDao getInstance() {
        if (restaurantDao == null) {
            restaurantDao = new RestaurantDao();
        }
        return restaurantDao;
    }


    @Override
    public Restaurant find(int id) {
        Restaurant restaurant = null;
        String sql = "SELECT * FROM restaurants WHERE id="+ id;
        try {
            ResultSet rs = db.execute(sql);

            while (rs.next()) {
                restaurant = new Restaurant(rs.getInt("id"), rs.getString("name"),
                        rs.getString("state"), rs.getString("city"), rs.getString("address"),
                        rs.getDouble("latitude"), rs.getDouble("longitude"));

            }

        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return restaurant;
    }

    @Override
    public List<Restaurant> getAll() {
        return null;
    }

    public List<Restaurant> get(String city) {
        String sql = "SELECT * FROM restaurants WHERE city='"+ city +"'";
        //System.out.println(sql);
        List<Restaurant> restaurants = new ArrayList<>();
        try {
            ResultSet rs = db.execute(sql);

            while (rs.next()) {
                Restaurant restaurant = new Restaurant(rs.getInt("id"), rs.getString("name"),
                        rs.getString("state"), rs.getString("city"), rs.getString("address"),
                        rs.getDouble("latitude"), rs.getDouble("longitude"));
                restaurants.add(restaurant);
            }

        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return restaurants;
    }

    @Override
    public Boolean store(Restaurant restaurant) {
        return true;
    }
}
