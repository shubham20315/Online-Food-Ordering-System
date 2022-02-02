package com.iiitd.daos;

import com.iiitd.models.Rating;

import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.List;

public class RatingDao implements Dao<Rating>{

    private static RatingDao ratingDao;

    private RatingDao(){
        db.createRatingsTable();
    }

    public static RatingDao getInstance() {
        if (ratingDao == null) {
            ratingDao = new RatingDao();
        }
        return ratingDao;
    }

    @Override
    public Rating find(int id) {
        return null;
    }

    @Override
    public List<Rating> getAll() {
        return null;
    }

    @Override
    public Boolean store(Rating rating) {
        String sql = "INSERT INTO ratings" +
                "(user_id, app, food) " +
                "VALUES(?,?,?)";

        try {

            PreparedStatement pstmt = db.conn.prepareStatement(sql);

            pstmt.setInt(1, rating.getUserId());
            pstmt.setInt(2, rating.getApp());
            pstmt.setDouble(3, rating.getFood());
            pstmt.executeUpdate();

            return true;
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return false;
    }
}
