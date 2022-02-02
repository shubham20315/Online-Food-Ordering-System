package com.iiitd.models;

import com.iiitd.daos.RestaurantDao;
import java.util.List;

public class Restaurant {

    protected int id;
    protected String name;
    protected String state;
    protected String city;
    protected String address;
    protected double latitude;
    protected double longitude;

    RestaurantDao restaurantDao;

    public Restaurant(){
        restaurantDao = RestaurantDao.getInstance();
    }

    public Restaurant(int id, String name, String state, String city, String address, double latitude, double longitude) {
        this.id = id;
        this.name = name;
        this.state = state;
        this.city = city;
        this.address = address;
        this.latitude = latitude;
        this.longitude = longitude;

        restaurantDao = RestaurantDao.getInstance();
    }

    /*getters and setters*/
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public double getLatitude() {
        return latitude;
    }

    public void setLatitude(double latitude) {
        this.latitude = latitude;
    }

    public double getLongitude() {
        return longitude;
    }

    public void setLongitude(double longitude) {
        this.longitude = longitude;
    }

    /**
     * method to display restaurants within a city
     */
    public List<Restaurant> show(User user) {

        List<Restaurant> restaurants = restaurantDao.get(user.getCity());
        int seq = 1;
        for (Restaurant restaurant : restaurants) {
            int estimatedTime = new Track().calculateEstimatedTime(user, restaurant);
            System.out.println(seq + ".\t" + restaurant.name + "\tETA: " + estimatedTime + " mins");
            seq ++;
        }
        return restaurants;
    }

    /**
     * method to retrieve a restaurant
     */
    public Restaurant find(int id) {
        return restaurantDao.find(id);
    }


}
