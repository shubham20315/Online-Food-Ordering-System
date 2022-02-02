package com.iiitd.daos;

import com.iiitd.models.City;
import com.iiitd.models.Menu;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class MenuDao implements Dao<Menu>{

    private static MenuDao menuDao;

    private MenuDao(){
        db.createMenuTable();
    }

    public static MenuDao getInstance() {
        if (menuDao == null) {
            menuDao = new MenuDao();
        }
        return menuDao;
    }

    @Override
    public Menu find(int id) {
        return null;
    }

    @Override
    public List getAll() {
        return null;
    }

    public List get(int restaurantId) {
        String sql = "SELECT * FROM menu WHERE restaurant_id='"+ restaurantId +"'";
        List<Menu> menu = new ArrayList<>();
        try {
            ResultSet rs = db.execute(sql);

            while (rs.next()) {
                Menu item = new Menu(rs.getInt("id"), rs.getInt("restaurant_id"),
                        rs.getString("name"), rs.getDouble("price"));
                menu.add(item);
            }

        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return menu;
    }

    @Override
    public Boolean store(Menu menu) {
        return true;
    }
}
