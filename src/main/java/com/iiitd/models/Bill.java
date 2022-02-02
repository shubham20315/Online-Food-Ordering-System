package com.iiitd.models;

import com.iiitd.daos.CartDao;

import java.util.List;

import static java.lang.Math.ceil;

public class Bill {
    private double delivery;
    private double subTotal;
    private double totalBill;

    /**
     * method to calculate delivery charges
     */
    public double getDeliveryCharges(User user, Restaurant restaurant){
        double distance = Distance.get(user.getLatitude(), user.getLongitude(), restaurant.getLatitude(), restaurant.getLongitude());
        delivery = ceil(distance * 5);
        return delivery;
    }

    /**
     * method to calculate subtotal (only the item prices)
     */
    public double getSubtotal(int userId){
        CartDao cartDao = CartDao.getInstance();
        List<Cart> cart = cartDao.get(userId);
        subTotal = 0;
        for (Cart item : cart) {
            subTotal += item.getItemTotal();
        }
        return subTotal;
    }

    /**
     * method to calculate the total bill
     */
    public double calculateBill(Promotion promotion){
        totalBill = subTotal + delivery;
        if(promotion.isValid()){
            totalBill -= promotion.getValue();
        }
        return totalBill;

    }


}
