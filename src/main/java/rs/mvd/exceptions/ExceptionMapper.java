package rs.mvd.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;
import rs.mvd.factories.ResponseFactory;
import rs.mvd.response.Responses;

@ControllerAdvice
public class ExceptionMapper extends ResponseEntityExceptionHandler {

    @ExceptionHandler(value = NotFoundException.class)
    public final ResponseEntity<Object> handleNotFoundException(NotFoundException ex) {
        Responses notFound = ResponseFactory.notFound(ex.getMessage());
        return new ResponseEntity<>(notFound, HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(value = BadRequestException.class)
    public final ResponseEntity<Object> handleBadRequestException(BadRequestException ex) {
        Responses badRequest = ResponseFactory.badRequest(ex.getMessage());
        return ResponseEntity.badRequest().body(badRequest);
    }

    @ExceptionHandler(Exception.class)
    public final ResponseEntity<Object> handleUncaughtException(Exception ex) {
        ex.printStackTrace();
        Responses internalServerError = ResponseFactory.internalServerError();
        return new ResponseEntity<>(internalServerError, HttpStatus.INTERNAL_SERVER_ERROR);
    }

}
