package kg.giftlist.giftlist.exception.handler;

import kg.giftlist.giftlist.exception.*;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(NotFoundException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public ExceptionResponse handleNotFoundException(NotFoundException e) {
        return new ExceptionResponse(HttpStatus.NOT_FOUND,
                e.getClass().getSimpleName(),
                e.getMessage());
    }

    @ExceptionHandler(AlreadyExistException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ExceptionResponse handleAlreadyExistException(AlreadyExistException e) {
        return new ExceptionResponse(HttpStatus.BAD_REQUEST,
                e.getClass().getSimpleName(),
                e.getMessage());
    }

    @ExceptionHandler(GiftForbiddenException.class)
    @ResponseStatus(HttpStatus.FORBIDDEN)
    public ExceptionResponse handleForbiddenFoundException(GiftForbiddenException e) {
        return new ExceptionResponse(HttpStatus.FORBIDDEN,
                e.getClass().getSimpleName(),
                e.getMessage());
    }

    @ExceptionHandler(WishNotFoundException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public ExceptionResponse handleWishNotFoundException(WishNotFoundException e) {
        return new ExceptionResponse(HttpStatus.NOT_FOUND,
                e.getClass().getSimpleName(),
                e.getMessage());
    }


}