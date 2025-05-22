package az.hamburg.autoservice.exception.handler;

public class WrongPhoneNumberException extends RuntimeException {
    public WrongPhoneNumberException(String message, String code) {
        super(message);
    }
}
