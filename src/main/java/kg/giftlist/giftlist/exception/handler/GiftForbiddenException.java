package kg.giftlist.giftlist.exception.handler;


public class GiftForbiddenException extends RuntimeException{

    public GiftForbiddenException() {
    }

    public GiftForbiddenException(String message) {
        super(message);
    }
}
