package com.iiitd.daos;

import com.iiitd.models.City;

import java.sql.*;
import java.util.List;

public class CityDao implements Dao<City>{

    private static CityDao cityDao;

    public static CityDao getInstance() {
        if (cityDao == null) {
            cityDao = new CityDao();
        }
        return cityDao;
    }

    @Override
    public City find(int id) {
        return null;
    }

    @Override
    public List<City> getAll() {
        return null;
    }

    @Override
    public Boolean store(City city) {
        return null;
    }

    public ResultSet getAllByState(String state) {
        String sql = "SELECT cities.* FROM cities " +
                "JOIN states ON states.id=cities.state_id " +
                "WHERE states.name='" + state + "' ORDER BY cities.name";
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
