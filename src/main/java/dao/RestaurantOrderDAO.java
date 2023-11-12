package dao;


import domain.modelo.restaurant.RestaurantOrder;

import java.util.List;

public interface RestaurantOrderDAO {
    public List<RestaurantOrder> getAll();
    public List<RestaurantOrder> getByTable(int tableId);

    public RestaurantOrder get(int id);

    public Integer add(RestaurantOrder restaurantOrder);

    public Integer update(RestaurantOrder restaurantOrder);

    public Integer delete(int id);

}
