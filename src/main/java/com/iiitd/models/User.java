package com.iiitd.models;

import com.iiitd.daos.UserDao;
import java.sql.ResultSet;

public class User {
    private int id;
    private String firstName;
    private String lastName;
    private String email;
    private String password;
    private String state;
    private String city;
    private String pincode;
    private String address;

    private double latitude;
    private double longitude;

    UserDao userDao;

    /*getters and setters*/
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
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


    public String getPincode() {
        return pincode;
    }

    public void setPincode(String pincode) {
        this.pincode = pincode;
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

    public User() {
        userDao = UserDao.getInstance();
    }

    /**
     * method to create a new user
     */
    public String create() {

        if(userDao.store(this)){
            return "Registration successful!";
        } else{
            return "Registration failed!";
        }
    }

    /**
     * method to verify the user on login attempt
     */
    public Boolean authenticate(String email, String password) {
        ResultSet rs = userDao.getUserByEmailAndPassword(email, password);
        Boolean found = false;
        try{
            while (rs.next()) {
                found = true;
                id = rs.getInt("id");
                firstName = rs.getString("first_name");
                lastName = rs.getString("last_name");
                this.email = rs.getString("email");
                this.password = rs.getString("password");
                state = rs.getString("state");
                city = rs.getString("city");
                pincode = rs.getString("pincode");
                address = rs.getString("address");
                latitude = rs.getDouble("latitude");
                longitude = rs.getDouble("longitude");
            }
        } catch(Exception e){
            System.out.println(e.getMessage());
        }
        if(!found){
            return false;
        } else{
            return true;
        }

    }

    /**
     * method to check if the email entered does not already exist
     */
    public Boolean checkEmail(String email) {
        return userDao.findEmail(email);
    }

}
