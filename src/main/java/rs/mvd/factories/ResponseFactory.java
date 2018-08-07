package rs.mvd.factories;

import org.springframework.http.HttpStatus;
import rs.mvd.response.Responses;

public class ResponseFactory {

    public static Responses ok(String message) {
        return new Responses(200, message);
    }

    public static Responses ok(String message, Object object) {
        return new Responses(200, message, object);
    }

    public static Responses badRequest(String message) {
        return new Responses(HttpStatus.BAD_REQUEST.value(), message);
    }

    public static Responses notFound(String message) {
        return new Responses(HttpStatus.NOT_FOUND.value(), message);
    }

    public static Responses internalServerError() {
        return new Responses(HttpStatus.INTERNAL_SERVER_ERROR.value(), "Something went wrong.");
    }
}
