package com.iiitd.models;

import com.iiitd.daos.CartDao;

import java.util.List;

public class Cart {
    private int id;
    private int restaurantId;
    private int userId;
    private int itemId;
    private int quantity;
    private String itemName;
    private double itemTotal;

    /*getters and setters*/
    public String getItemName() {
        return itemName;
    }

    public void setItemName(String itemName) {
        this.itemName = itemName;
    }

    public double getItemTotal() {
        return itemTotal;
    }

    public void setItemTotal(double itemTotal) {
        this.itemTotal = itemTotal;
    }

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

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
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

    CartDao cartDao;

    public Cart(){
        cartDao = CartDao.getInstance();
    }

    public Cart(int userId, int restaurantId, int itemId, int quantity){
        this.restaurantId = restaurantId;
        this.userId = userId;
        this.itemId = itemId;
        this.quantity = quantity;

        cartDao = CartDao.getInstance();
    }

    public Cart(int id, int userId, int restaurantId, int itemId, int quantity){
        this.id = id;
        this.restaurantId = restaurantId;
        this.userId = userId;
        this.itemId = itemId;
        this.quantity = quantity;

        cartDao = CartDao.getInstance();
    }

    /**
     * method to display the cart
     */
    public List<Cart> show(int userId){

        List<Cart> cart = cartDao.get(userId);

        if(cart.isEmpty()){
            System.out.println("Cart is empty!");
        } else{
            int seq = 1;
            System.out.println(String.format("%40s", "########## CART ##########"));
            System.out.println(String.format("%5s %25s %10s %15s", "", "Item Name",  "Quantity", "Item Total"));
            for (Cart item : cart) {
                System.out.println(String.format("%5s %25s %10s %15s", seq, item.getItemName(), item.getQuantity(), item.getItemTotal()));
                seq ++;
            }
        }

        return cart;
    }

    /**
     * method to get cart contents
     */
    public List<Cart> get(int userId){

        List<Cart> cart = cartDao.get(userId);

        return cart;
    }

    /**
     * method to add new item to cart
     */
    public Boolean addItem(){
        if(cartDao.checkRestaurant(this.userId, this.restaurantId)){
            return cartDao.store(this);
        } else{
            System.out.println("You cannot add items from another restaurant!!");
            System.out.println("Please remove all items from cart to order from this restaurant");
            return false;
        }

    }

    /**
     * method to remove all items from cart
     */
    public void empty(int userId){
        if(cartDao.deleteAll(userId)){
            System.out.println("All items removed from cart!");
        } else{
            System.out.println("Cart could not be emptied!");
        }
    }

    /**
     * method to remove a particular item from cart
     */
    public void removeItem(int cartId){
        if(cartDao.deleteItem(cartId)){
            System.out.println("Item removed from cart!");
        } else{
            System.out.println("Item could not be removed from cart!");
        }
    }


}
