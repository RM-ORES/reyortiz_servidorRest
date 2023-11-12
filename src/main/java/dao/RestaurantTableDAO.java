package dao;


import domain.modelo.restaurant.RestaurantTable;

import java.util.List;

public interface RestaurantTableDAO {
    List<RestaurantTable> getAll();

    RestaurantTable get(int id);

    int add(RestaurantTable restaurantTable);

    int update(RestaurantTable restaurantTable);

    int delete(int id);
}
