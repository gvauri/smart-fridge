package css.ch.smartfridgejavabackend.exceptions;

public class InvalidItemAction extends RuntimeException {
    public InvalidItemAction(String message) {
        super(message);
    }
}
