package rs.mvd.response;


import com.fasterxml.jackson.annotation.JsonInclude;

public class Responses {

    private int statusCode;
    private String message;
    @JsonInclude(JsonInclude.Include.NON_NULL)
    private Object entity;

    public Responses() {
    }

    public Responses(int statusCode, String message) {
        this.statusCode = statusCode;
        this.message = message;
    }

    public Responses(int statusCode, String message, Object entity) {
        this.statusCode = statusCode;
        this.message = message;
        this.entity = entity;
    }

    public int getStatusCode() {
        return statusCode;
    }

    public void setStatusCode(int statusCode) {
        this.statusCode = statusCode;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public Object getEntity() {
        return entity;
    }

    public void setEntity(Object entity) {
        this.entity = entity;
    }

    @Override
    public String toString() {
        return "Responses{" +
                "statusCode=" + statusCode +
                ", message='" + message + '\'' +
                ", entity=" + entity +
                '}';
    }

}
