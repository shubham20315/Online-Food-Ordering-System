package com.iiitd.models;

public class Payment {
    Boolean success = false;

    // method to pay for an order
    public void pay(int userId, int restaurantId, double amountPaid, double discount){
        Order order = new Order();
        order.setUserId(userId);
        order.setRestaurantId(restaurantId);
        order.setAmountPaid(amountPaid);
        order.setDiscount(discount);
        order.create();

        success = true;
    }

    // method to check if the payment succeeded
    public Boolean isPaymentSuccessful(){
        if(success){
            return true;
        } else{
            return false;
        }
    }
}
