package az.hamburg.autoservice.exception.handler;

public class VehicleNotFoundException extends RuntimeException {

    public VehicleNotFoundException(String message,String code) {
        super(message);

    }
}
