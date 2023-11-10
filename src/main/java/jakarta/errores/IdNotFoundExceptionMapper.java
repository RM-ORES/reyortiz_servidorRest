package jakarta.errores;

import domain.modelo.errores.IdNotFoundException;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import jakarta.ws.rs.ext.ExceptionMapper;

import java.time.LocalDateTime;

public class IdNotFoundExceptionMapper implements ExceptionMapper<IdNotFoundException> {

    @Override
    public Response toResponse(IdNotFoundException exception) {
        RestError restError = new RestError(exception.getMessage(), LocalDateTime.now());
        return Response.status(Response.Status.NOT_FOUND).entity(restError)
                .type(MediaType.APPLICATION_JSON_TYPE).build();
    }
}
