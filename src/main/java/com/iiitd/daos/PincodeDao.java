package com.iiitd.daos;

import com.iiitd.models.Pincode;

import java.sql.*;
import java.util.List;

public class PincodeDao implements Dao<Pincode>{

    private static PincodeDao pincodeDao;

    public static PincodeDao getInstance() {
        if (pincodeDao == null) {
            pincodeDao = new PincodeDao();
        }
        return pincodeDao;
    }

    @Override
    public Pincode find(int id) {
        return null;
    }

    @Override
    public List<Pincode> getAll() {
        return null;
    }

    @Override
    public Boolean store(Pincode pincode) {
        return null;
    }

    public ResultSet getByPincode(String pincode){
        String sql = "SELECT latitude, longitude FROM pincodes WHERE pincode='"+ pincode +"'";
        ResultSet rs = null;
        try {
            Statement stmt = db.conn.createStatement();
            rs = stmt.executeQuery(sql);


        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return rs;
    }
}
