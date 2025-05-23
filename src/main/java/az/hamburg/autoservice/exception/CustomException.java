package az.hamburg.autoservice.exception;

import az.hamburg.autoservice.exception.handler.*;
import ch.qos.logback.core.model.processor.ModelHandlerException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ProblemDetail;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import static org.springframework.http.HttpStatus.*;

@Slf4j
@RestControllerAdvice
public class CustomException {

    @ExceptionHandler(UserNotFoundException.class)
    @ResponseStatus(NOT_FOUND)
    public ProblemDetail handlerUserNotFound(UserNotFoundException e){
        return ProblemDetail.forStatusAndDetail(NOT_FOUND, e.getMessage());
    }

    @ExceptionHandler(MechanicNotFoundException.class)
    @ResponseStatus(NOT_FOUND)
    public ProblemDetail handlerMechanicNotFound(MechanicNotFoundException e){
        return ProblemDetail.forStatusAndDetail(NOT_FOUND, e.getMessage());
    }

    @ExceptionHandler(UserAlreadyExistsException.class)
    @ResponseStatus(CONFLICT)
    public ProblemDetail handlerUserAlreadyExists(UserAlreadyExistsException e){
        return ProblemDetail.forStatusAndDetail(CONFLICT, e.getMessage());
    }

    @ExceptionHandler(MechanicAlreadyExistsException.class)
    @ResponseStatus(CONFLICT)
    public ProblemDetail handlerMechanicAlreadyExists(MechanicAlreadyExistsException e){
        return ProblemDetail.forStatusAndDetail(CONFLICT, e.getMessage());
    }

    @ExceptionHandler(VehicleNotFoundException.class)
    @ResponseStatus(NOT_FOUND)
    public ProblemDetail handlerVehicleNotFound(VehicleNotFoundException e){
        return ProblemDetail.forStatusAndDetail(NOT_FOUND, e.getMessage());
    }

    @ExceptionHandler(AppointmentNotFoundException.class)
    @ResponseStatus(NOT_FOUND)
    public ProblemDetail handlerAppointmentNotFound(AppointmentNotFoundException e){
        return ProblemDetail.forStatusAndDetail(NOT_FOUND, e.getMessage());
    }

    @ExceptionHandler(AppointmentMechanicNotFoundException.class)
    @ResponseStatus(NOT_FOUND)
    public ProblemDetail handlerAppointmentMechanicNotFound(AppointmentMechanicNotFoundException e){
        return ProblemDetail.forStatusAndDetail(NOT_FOUND, e.getMessage());
    }

    @ExceptionHandler(EmailAlreadyExistsException.class)
    @ResponseStatus(CONFLICT)
    public ProblemDetail handlerEmailAlreadyExists(EmailAlreadyExistsException e){
        return ProblemDetail.forStatusAndDetail(CONFLICT, e.getMessage());
    }

    @ExceptionHandler(PlateNumberAlreadyExistsException.class)
    @ResponseStatus(CONFLICT)
    public ProblemDetail handlerPlateNumberAlreadyExists(PlateNumberAlreadyExistsException e){
        return ProblemDetail.forStatusAndDetail(CONFLICT, e.getMessage());
    }

    @ExceptionHandler(WrongPhoneNumberException.class)
    @ResponseStatus(BAD_REQUEST)
    public ProblemDetail handlerWrongPhoneNumber(WrongPhoneNumberException e){
        return ProblemDetail.forStatusAndDetail(BAD_REQUEST, e.getMessage());
    }

}
