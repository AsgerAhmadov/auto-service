package az.hamburg.autoservice.exception.handler;

public class UserAlreadyExistsException extends RuntimeException {
    public UserAlreadyExistsException(String message, String code) {
        super(message);
    }
}
