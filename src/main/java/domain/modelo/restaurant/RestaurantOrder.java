package domain.modelo.restaurant;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class RestaurantOrder {
    private int id;
    private int tableNumber;
    private int idCustomer;
    private LocalDateTime date;

}
