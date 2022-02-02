package com.iiitd.models;

import com.iiitd.daos.PincodeDao;
import java.sql.*;

public class Pincode {

    double latitude;
    double longitude;

    PincodeDao pincodeDao;

    /*getters and setters*/
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

    public Pincode() {
        latitude = 0;
        longitude = 0;

        pincodeDao = PincodeDao.getInstance();
    }

    /**
     * method to retrieve latitude and longitude for a pincode
     */
    public void findLatitudeAndLongitude(String pincode) {
        ResultSet rs = pincodeDao.getByPincode(pincode);
        try{
            while (rs.next()) {
                latitude = rs.getDouble("latitude");
                longitude = rs.getDouble("longitude");
            }
            //System.out.println(latitude);
            //System.out.println(longitude);
        } catch (Exception e){
            System.out.println(e.getMessage());
        }
    }
}
