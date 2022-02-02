package com.iiitd.models;

public class SAVE50 implements PromoCode {

    @Override
    public String getCode() {
        return "SAVE50";
    }

    @Override
    public double getValue() {
        return 50;
    }
}
