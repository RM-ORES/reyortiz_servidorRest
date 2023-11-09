package domain.servicios;


import dao.RestaurantOrderDAO;

import domain.modelo.restaurant.RestaurantOrder;
import jakarta.inject.Inject;
import lombok.extern.log4j.Log4j2;

import java.util.List;


@Log4j2
public class RestaurantOrderServices {
    private final RestaurantOrderDAO restaurantOrderDAO;

    @Inject
    public RestaurantOrderServices(RestaurantOrderDAO restaurantOrderDAO) {
        this.restaurantOrderDAO = restaurantOrderDAO;
    }

    public List<RestaurantOrder> getAll() {
        return restaurantOrderDAO.getAll();
    }



    public RestaurantOrder get(int id) {
        return restaurantOrderDAO.get(id);
    }

    public Integer add(RestaurantOrder restaurantOrder) {
            return restaurantOrderDAO.add(restaurantOrder);
    }

    public Integer update(RestaurantOrder restaurantOrder) {
        return restaurantOrderDAO.update(restaurantOrder);
    }

    public Integer delete(int id) {
        return restaurantOrderDAO.delete(id);
    }


}
