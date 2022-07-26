package kg.giftlist.giftlist.exception.handler;

import kg.giftlist.giftlist.exception.ExceptionResponse;
import kg.giftlist.giftlist.exception.IsEmptyException;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class GlobalExceptionHandler {
    //404
    @ExceptionHandler(IsEmptyException.class)
    @ResponseStatus(HttpStatus.ACCEPTED)
    public ExceptionResponse handlerObjectNotFoundException(IsEmptyException e) {
        return new ExceptionResponse(
                HttpStatus.ACCEPTED,
                e.getClass().getSimpleName(),
                e.getMessage()
        );
    }
}