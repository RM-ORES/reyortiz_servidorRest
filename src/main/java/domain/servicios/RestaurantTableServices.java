package domain.servicios;

import dao.RestaurantTableDAO;
import jakarta.inject.Inject;
import domain.modelo.restaurant.RestaurantTable;

import java.util.List;

public class RestaurantTableServices {
    private final RestaurantTableDAO restaurantTableDAO;

    @Inject
    public RestaurantTableServices(RestaurantTableDAO restaurantTableDAO) {
        this.restaurantTableDAO = restaurantTableDAO;
    }

    public List<RestaurantTable> getAll() {
        return restaurantTableDAO.getAll();
    }

    public RestaurantTable get(int id) {
        return restaurantTableDAO.get(id);
    }

    public void add(RestaurantTable restaurantTable) {
        restaurantTableDAO.add(restaurantTable);
    }

    public void update(RestaurantTable restaurantTable) {
        restaurantTableDAO.update(restaurantTable);
    }

    public int delete(int id) {
        return restaurantTableDAO.delete(id);
    }
}
