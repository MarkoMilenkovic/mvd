package rs.mvd.exceptions;

public class AbstractException extends RuntimeException {

    int status;

    protected AbstractException(String message, int status) {
        super(message);
        this.status = status;
    }

}
