package domain.modelo.restaurant;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class RestaurantTable {
    private int tableNumber;
    private int seats;
}
