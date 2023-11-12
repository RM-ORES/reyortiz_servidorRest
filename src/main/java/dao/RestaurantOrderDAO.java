package dao;


import domain.modelo.restaurant.RestaurantOrder;

import java.util.List;

public interface RestaurantOrderDAO {
    List<RestaurantOrder> getAll();

    List<RestaurantOrder> getByTable(int tableId);

    RestaurantOrder get(int id);

    Integer add(RestaurantOrder restaurantOrder);

    Integer update(RestaurantOrder restaurantOrder);

    Integer delete(int id);

}
