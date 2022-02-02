package com.iiitd.daos;

import com.iiitd.models.Promotion;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class PromotionDao implements Dao<Promotion> {

    private static PromotionDao promotionDao;

    private PromotionDao(){
        db.createPromotionsTable();
    }

    public static PromotionDao getInstance() {
        if (promotionDao == null) {
            promotionDao = new PromotionDao();
        }
        return promotionDao;
    }

    public List get(int userId){
        String sql = "SELECT * FROM promotions WHERE user_id='"+ userId +"'";
        List<Promotion> promotions = new ArrayList<>();
        try {
            ResultSet rs = db.execute(sql);

            while (rs.next()) {
                Promotion promotion = new Promotion(rs.getInt("id"), rs.getInt("user_id"),
                        rs.getString("promo_code"), rs.getInt("order_id"));
                promotions.add(promotion);
            }

        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return promotions;

    }

    @Override
    public Promotion find(int id) {
        return null;
    }

    @Override
    public List<Promotion> getAll() {
        return null;
    }

    @Override
    public Boolean store(Promotion promotion) {
        return null;
    }
}
