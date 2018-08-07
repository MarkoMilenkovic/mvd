package rs.mvd.factories;

import rs.mvd.response.Responses;

public class ResponseFactory {

    public static Responses ok(String message) {
        return new Responses(200, message);
    }

    public static Responses ok(String message, Object object) {
        return new Responses(200, message, object);
    }

    public static Responses badRequest(String message) {
        return new Responses(javax.ws.rs.core.Response.Status.BAD_REQUEST.getStatusCode(), message);
    }

}
