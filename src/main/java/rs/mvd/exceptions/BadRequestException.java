package rs.mvd.exceptions;

import javax.ws.rs.core.Response;
import javax.ws.rs.ext.Provider;

@Provider
public class BadRequestException extends RuntimeException {

    public BadRequestException(String message) {
        super(message);
    }

}
