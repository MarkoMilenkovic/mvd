package rs.mvd.exceptions;

import javax.ws.rs.core.Response;
import javax.ws.rs.ext.ExceptionMapper;
import javax.ws.rs.ext.Provider;

@Provider
public class AbstractExceptionMapper implements ExceptionMapper<AbstractException> {

    @Override
    public Response toResponse(AbstractException exception) {
        return Response.status(exception.status).entity(exception.getMessage()).build();
    }

}
