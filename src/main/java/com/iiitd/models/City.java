package com.iiitd.models;

import com.iiitd.daos.CityDao;
import com.iiitd.daos.Database;

import java.sql.*;

public class City {

    CityDao cityDao;

    public City() {
        cityDao = CityDao.getInstance();
    }

    /**
     * method to retrieve all cities within a state
     */
    public ResultSet getAll(String state) {
        return cityDao.getAllByState(state);
    }
}
