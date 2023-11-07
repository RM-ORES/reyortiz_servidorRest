package jakarta.errores;

import domain.modelo.errores.DataBaseDownException;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import jakarta.ws.rs.ext.ExceptionMapper;

import java.time.LocalDateTime;

public class DataBaseDownExceptionMapper implements ExceptionMapper<DataBaseDownException> {
    @Override
    public Response toResponse(DataBaseDownException exception) {
        RestError restError = new RestError(exception.getMessage(), LocalDateTime.now());
        return Response.status(Response.Status.INTERNAL_SERVER_ERROR).entity(restError)
                .type(MediaType.APPLICATION_JSON_TYPE).build();
    }
}
