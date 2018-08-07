package rs.mvd.exceptions;

import javax.ws.rs.core.Response;

public class NotFoundException extends AbstractException {

    public NotFoundException(String message) {
        super(message, Response.Status.NOT_FOUND.getStatusCode());
    }
}
