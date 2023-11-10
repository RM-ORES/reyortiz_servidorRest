package domain.modelo.restaurant;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class RestaurantOrder {
    private int id;
    private int tableNumber;
    private int idCustomer;
    private LocalDateTime date;

    public RestaurantOrder(int tableNumber, int idCustomer, LocalDateTime date) {
        this.tableNumber = tableNumber;
        this.idCustomer = idCustomer;
        this.date = date;
    }

    public RestaurantOrder(String line) {
        String[] part = line.split(",");

        this.id = Integer.parseInt(part[0]);
        this.tableNumber = Integer.parseInt(part[1]);
        this.idCustomer = Integer.parseInt(part[2]);
        date = LocalDateTime.parse(part[3]);
    }

    public String stringToTextFile() {
        return id + "," + tableNumber + "," + idCustomer + "," + date;
    }
}
