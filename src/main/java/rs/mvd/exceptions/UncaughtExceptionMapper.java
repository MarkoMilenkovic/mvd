package rs.mvd.exceptions;

import javax.ws.rs.core.Response;
import javax.ws.rs.ext.ExceptionMapper;
import javax.ws.rs.ext.Provider;
import java.util.logging.Level;
import java.util.logging.Logger;

@Provider
public class UncaughtExceptionMapper implements ExceptionMapper<Exception> {

    @Override
    public Response toResponse(Exception e) {
        Logger.getLogger(Exception.class.getName()).log(Level.SEVERE, e.getMessage(), e);
        return Response.status(Response.Status.INTERNAL_SERVER_ERROR).entity("Something went wrong!").build();
    }
}
