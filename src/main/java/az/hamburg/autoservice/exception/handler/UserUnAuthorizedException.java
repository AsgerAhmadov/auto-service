package az.hamburg.autoservice.exception.handler;

public class UserUnAuthorizedException extends RuntimeException {
    public UserUnAuthorizedException(String message, String code) {
        super(message);
    }
}
