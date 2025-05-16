package az.hamburg.autoservice.exception.handler;

public class AppointmentNotFoundException extends RuntimeException{

    public AppointmentNotFoundException(String message, String code){
        super(message);
    }
}
