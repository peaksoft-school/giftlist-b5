package kg.giftlist.giftlist.exception;

public class IsEmptyException extends RuntimeException {
    public IsEmptyException() {
    }

    public IsEmptyException(String message) {
        super(message);
    }
}
