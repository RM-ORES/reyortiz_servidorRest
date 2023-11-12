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

    public List<RestaurantOrder> getByTable(int tableId) {
        return restaurantOrderDAO.getByTable(tableId);
    }


    public RestaurantOrder get(int id) {
        return restaurantOrderDAO.get(id);
    }

    public void add(RestaurantOrder restaurantOrder) {
        restaurantOrderDAO.add(restaurantOrder);
    }

    public void update(RestaurantOrder restaurantOrder) {
        restaurantOrderDAO.update(restaurantOrder);
    }

    public Integer delete(int id) {
        return restaurantOrderDAO.delete(id);
    }


}
