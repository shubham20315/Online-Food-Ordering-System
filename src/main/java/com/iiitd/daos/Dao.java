package com.iiitd.daos;

import java.util.List;

public interface Dao<T> {
    Database db = Database.getInstance();

    T find(int id);

    List<T> getAll();

    Boolean store(T t);
}
