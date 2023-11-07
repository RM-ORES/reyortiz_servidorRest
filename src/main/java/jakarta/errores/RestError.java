package jakarta.errores;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.time.LocalDateTime;

@AllArgsConstructor
@ToString
public class RestError {
    private String mensaje;
    private LocalDateTime fecha;
}
