package store.exception;

public class InvalidPromotionException extends IllegalArgumentException{
    public InvalidPromotionException(String message) {
        super(message);
    }
}
