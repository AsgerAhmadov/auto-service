package az.hamburg.autoservice.exception.handler;

public class AppointmentNotCompleted extends RuntimeException{

    public AppointmentNotCompleted(String message,String code ) {

        super(message);
    }
}
