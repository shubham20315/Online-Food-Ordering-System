package com.iiitd.models;

import com.iiitd.daos.StateDao;
import java.util.List;

public class State {

    private String name;

    StateDao stateDao;

    /*getters and setters*/
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public State(){
        stateDao = StateDao.getInstance();
    }

    public State(String name){
        this.name = name;
    }

    /**
     * method to retrieve all states in India
     */
    public List<State> getAll() {
        return stateDao.getAll();
    }
}
