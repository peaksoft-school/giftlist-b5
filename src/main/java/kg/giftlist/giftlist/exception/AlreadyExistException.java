package kg.giftlist.giftlist.exception;


public class AlreadyExistException extends RuntimeException {

    public AlreadyExistException() {
    }

    public AlreadyExistException(String message) {
        super(message);
    }
}
