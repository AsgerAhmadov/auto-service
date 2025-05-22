package az.hamburg.autoservice.exception.handler;

public class PlateNumberAlreadyExistsException extends RuntimeException {
    public PlateNumberAlreadyExistsException(String message, String code) {
        super(message);
    }
}
