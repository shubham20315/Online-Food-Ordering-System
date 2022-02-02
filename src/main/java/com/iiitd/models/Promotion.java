package com.iiitd.models;

import com.iiitd.daos.PromotionDao;
import java.util.List;

public class Promotion {
    private int id;
    private int userId;
    private String promoCode;
    private int orderId;
    private Boolean valid;
    private double value;

    PromotionDao promotionDao;

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

    public String getPromoCode() {
        return promoCode;
    }

    public void setPromoCode(String promoCode) {
        this.promoCode = promoCode;
    }

    public int getOrderId() {
        return orderId;
    }

    public void setOrderId(int orderId) {
        this.orderId = orderId;
    }


    public Boolean isValid() {
        return valid;
    }

    public void setValid(Boolean valid) {
        this.valid = valid;
    }

    public double getValue() {
        return value;
    }

    public void setValue(double value) {
        this.value = value;
    }

    public Promotion(){
        valid = false;
        promotionDao = PromotionDao.getInstance();

    }

    public Promotion(int id, int userId, String promoCode, int orderId){
        this.id = id;
        this.userId = userId;
        this.promoCode = promoCode;
        this.orderId = orderId;

        promotionDao = PromotionDao.getInstance();
    }

    /**
     * method to check if promocode is valid
     */
    public Boolean checkPromoCode(int userId, String promoCode){
        PromoCode save20 = new SAVE20();
        PromoCode save50 = new SAVE50();

        if(!promoCode.equals(save20.getCode()) && !promoCode.equals(save50.getCode())){
            System.out.println("Please enter a valid promo code");
            return false;
        } else{

            List<Promotion> promotions = promotionDao.get(userId);

            for (Promotion promotion : promotions) {
                if(promotion.getPromoCode().equals(promoCode)){
                    System.out.println("You have already used this code");
                    return false;
                };
            }

            if(promoCode.equals(save20.getCode())){
                value = save20.getValue();
            } else if(promoCode.equals(save50.getCode())){
                value = save50.getValue();
            }
        }
        System.out.println("Promo code applied successfully");
        this.promoCode = promoCode;
        return true;
    }
}
