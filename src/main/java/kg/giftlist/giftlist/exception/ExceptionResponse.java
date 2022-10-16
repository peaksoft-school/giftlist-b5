package kg.giftlist.giftlist.exception;

import lombok.Getter;
import lombok.Setter;
import org.springframework.http.HttpStatus;

@Getter
@Setter
public class ExceptionResponse {

    private HttpStatus status;
    private String exceptionClassName;
    private String message;

    public ExceptionResponse(HttpStatus status, String exceptionClassName, String message) {
        this.status = status;
        this.exceptionClassName = exceptionClassName;
        this.message = message;
    }

}
