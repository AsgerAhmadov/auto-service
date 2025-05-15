package az.hamburg.autoservice.exception.handler;

public class UserNotFoundException extends RuntimeException {
  public UserNotFoundException(String message, String code) {
    super(message);
  }
}
