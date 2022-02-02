package com.iiitd.models;

import java.sql.Timestamp;
import java.text.ParseException;
import java.text.SimpleDateFormat;

import static java.lang.Math.ceil;

public class Track {
    private Timestamp estimatedTime;
    private int bufferTime;
    private int ETA;

    /*getters and setters*/
    public int getEstimatedTime() {
        return ETA;
    }

    public void setEstimatedTime(Timestamp estimatedTime) {
        this.estimatedTime = estimatedTime;
    }

    // method to calculate estimated time once order is placed
    public void calculateEstimatedTime(User user, Order order, Restaurant restaurant){

        /*System.out.println(user.getLatitude());
        System.out.println(user.getLongitude());
        System.out.println(restaurant.getLatitude());
        System.out.println(restaurant.getLatitude());*/
        double distance = Distance.get(user.getLatitude(), user.getLongitude(), restaurant.getLatitude(), restaurant.getLongitude());

        //System.out.println(distance + "kms");
        int deliveryTime = (int) ceil(distance * 5);
        int packingTime = 15;

        Timestamp ts = Timestamp.valueOf(order.getCreatedAt());

        //System.out.println(ts);
        ts.setMinutes(ts.getMinutes() + deliveryTime + packingTime);
        /*System.out.println(ts);
        System.out.println(deliveryTime);
        System.out.println(packingTime);*/


        int buffer = (int) ceil((deliveryTime + packingTime) * .1);

        estimatedTime = ts;
        //System.out.println(ts);
        int t = (int) ceil((ts.getTime() - System.currentTimeMillis()) / 1000 / 60);
        //System.out.println(t);
        ETA = t;


        bufferTime = buffer;

    }

    // method to calculate estimated delivery time
    public int calculateEstimatedTime(User user, Restaurant restaurant){

        double distance = Distance.get(user.getLatitude(), user.getLongitude(), restaurant.getLatitude(), restaurant.getLongitude());
        //System.out.println(distance + "kms");
        int deliveryTime = (int) ceil(distance * 5);
        int packingTime = 15;

        return deliveryTime + packingTime;

    }

    // method to check if the time has exceeded by 10% of the quoted time
    public boolean hasTimeExceeded(){

        Timestamp current = new Timestamp(System.currentTimeMillis());
        Timestamp estimatedTimeWithBuffer = estimatedTime;
        estimatedTimeWithBuffer.setMinutes(estimatedTimeWithBuffer.getMinutes() + bufferTime);
        if(current.getTime() > estimatedTimeWithBuffer.getTime()){
            return true;
        }
        return false;
    }
}
