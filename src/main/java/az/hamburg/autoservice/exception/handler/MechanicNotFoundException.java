package az.hamburg.autoservice.exception.handler;

public class MechanicNotFoundException extends RuntimeException {
    public MechanicNotFoundException(String message, String code) {
        super(message);
    }
}
