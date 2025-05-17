package az.hamburg.autoservice.exception.handler;

public class AppointmentMechanicNotFoundException extends RuntimeException{

    public AppointmentMechanicNotFoundException(String message, String code){
        super(message);
    }
}
