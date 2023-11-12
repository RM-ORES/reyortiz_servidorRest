package jakarta.errores;

import domain.modelo.errores.NotFoundException;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import jakarta.ws.rs.ext.ExceptionMapper;
import jakarta.ws.rs.ext.Provider;

import java.time.LocalDateTime;

@Provider
public class NotFoundExceptionMapper implements ExceptionMapper<NotFoundException> {

    @Override
    public Response toResponse(NotFoundException exception) {
        RestError restError = new RestError(exception.getMessage(), LocalDateTime.now());
        return Response.status(Response.Status.NOT_FOUND).entity(restError)
                .type(MediaType.APPLICATION_JSON_TYPE).build();
    }
}
