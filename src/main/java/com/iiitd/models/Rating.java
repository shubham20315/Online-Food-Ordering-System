package com.iiitd.models;

import com.iiitd.daos.RatingDao;
import java.sql.Timestamp;

public class Rating {
    int id;
    int userId;
    int app;
    int food;
    Timestamp createdAt;

    RatingDao ratingDao;

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

    public int getApp() {
        return app;
    }

    public void setApp(int app) {
        this.app = app;
    }

    public int getFood() {
        return food;
    }

    public void setFood(int food) {
        this.food = food;
    }

    public Timestamp getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(Timestamp createdAt) {
        this.createdAt = createdAt;
    }

    public Rating(){
        ratingDao = RatingDao.getInstance();
    }

    /**
     * method to save rating of food and app
     */
    public Boolean save(int userId, int app, int food){
        this.userId = userId;
        this.app = app;
        this.food = food;
        return ratingDao.store(this);
    }
}
