package com.iiitd.models;

public class Netbanking extends Payment {
    String username;
    String password;

    /*getters and setters*/
    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
