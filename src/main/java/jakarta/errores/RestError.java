package jakarta.errores;

import lombok.*;

import java.time.LocalDateTime;

@Builder
@Getter
@Setter
@AllArgsConstructor
@ToString
public class RestError {
    private String mensaje;
    private LocalDateTime fecha;
}
