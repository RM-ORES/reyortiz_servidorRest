package dao;


import domain.modelo.restaurant.RestaurantTable;

import java.util.List;

public interface RestaurantTableDAO {
    public List<RestaurantTable> getAll();

    public RestaurantTable get(int id);

    public int add(RestaurantTable restaurantTable);

    public int update(RestaurantTable restaurantTable);

    public int delete(int id);
}
