package az.hamburg.autoservice.exception.handler;

public class EmailAlreadyExistsException extends RuntimeException {
    public EmailAlreadyExistsException(String message, String code) {
        super(message);
    }
}
