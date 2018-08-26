package rs.mvd.exceptions;

import org.hibernate.validator.internal.engine.path.PathImpl;

import javax.validation.ConstraintViolation;
import javax.validation.ConstraintViolationException;
import javax.ws.rs.core.Response;
import javax.ws.rs.ext.ExceptionMapper;
import javax.ws.rs.ext.Provider;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;

@Provider
public class ConstraintViolationExceptionMapper implements ExceptionMapper<ConstraintViolationException> {



    @Override
    public Response toResponse(ConstraintViolationException exception) {
        Map<String, String> map = new HashMap<>();
        Set<ConstraintViolation<?>> constraintViolations = exception.getConstraintViolations();
        for (ConstraintViolation<?> constraintViolation : constraintViolations) {
            map.put(((PathImpl) constraintViolation.getPropertyPath()).getLeafNode().getName() ,constraintViolation.getMessage());
        }
        return Response.status(Response.Status.BAD_REQUEST).entity(map).build();
    }
}
