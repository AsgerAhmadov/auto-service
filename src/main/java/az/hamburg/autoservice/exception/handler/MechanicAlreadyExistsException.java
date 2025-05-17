package az.hamburg.autoservice.exception.handler;

public class MechanicAlreadyExistsException extends RuntimeException {
    public MechanicAlreadyExistsException(String message, String code) {
        super(message);
    }
}
