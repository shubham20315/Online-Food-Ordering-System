package com.iiitd.daos;

import com.iiitd.models.State;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class StateDao implements Dao<State>{

    private static StateDao stateDao;

    public static StateDao getInstance() {
        if (stateDao == null) {
            stateDao = new StateDao();
        }
        return stateDao;
    }

    @Override
    public State find(int id) {
        return null;
    }

    @Override
    public List<State> getAll() {
        String sql = "SELECT * FROM states ORDER BY name";
        List<State> states = new ArrayList<>();
        try {
            Statement stmt = db.conn.createStatement();
            ResultSet rs = stmt.executeQuery(sql);
            while(rs.next()){
                State state = new State(rs.getString("name"));
                states.add(state);
            }

        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return states;
    }

    @Override
    public Boolean store(State state) {
        return null;
    }
}
