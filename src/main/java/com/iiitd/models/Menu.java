package com.iiitd.models;

import com.iiitd.daos.MenuDao;
import java.util.List;

public class Menu extends Restaurant {
    private int id;
    private int restaurantId;
    private String name;
    private double price;

    MenuDao menuDao;

    public Menu(){
        menuDao = MenuDao.getInstance();
    }

    public Menu(int id, int restaurantId, String name, double price){
        this.id = id;
        this.restaurantId = restaurantId;
        this.name = name;
        this.price = price;

        menuDao = MenuDao.getInstance();
    }

    /*getters and setters*/
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getRestaurantId() {
        return restaurantId;
    }

    public void setRestaurantId(int restaurantId) {
        this.restaurantId = restaurantId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    /**
     * method to display the menu of a restaurant
     */
    public List<Menu> show(int restaurantId){
        List<Menu> menu = menuDao.get(restaurantId);
        if(!menu.isEmpty()){
            int seq = 1;
            System.out.println("### "+super.find(menu.get(0).restaurantId).name + " ###");
            System.out.println("Select an item");
            for (Menu item : menu) {
                System.out.println(seq + ".\t" + item.name + "\t" + item.price);
                seq ++;
            }
        } else{
            System.out.println("No item available");
        }
        return menu;
    }
}
