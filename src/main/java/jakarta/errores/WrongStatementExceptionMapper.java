package jakarta.errores;

import domain.modelo.errores.WrongStatementException;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import jakarta.ws.rs.ext.ExceptionMapper;

import java.time.LocalDateTime;

public class WrongStatementExceptionMapper implements ExceptionMapper<WrongStatementException> {
    @Override
    public Response toResponse(WrongStatementException exception) {
        RestError restError = new RestError(exception.getMessage(), LocalDateTime.now());
        return Response.status(Response.Status.BAD_REQUEST).entity(restError)
                .type(MediaType.APPLICATION_JSON_TYPE).build();
    }
}
