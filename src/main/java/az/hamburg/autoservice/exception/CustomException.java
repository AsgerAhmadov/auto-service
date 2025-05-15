package az.hamburg.autoservice.exception;

import az.hamburg.autoservice.exception.handler.MechanicAlreadyExistsException;
import az.hamburg.autoservice.exception.handler.MechanicNotFoundException;
import az.hamburg.autoservice.exception.handler.UserAlreadyExistsException;
import az.hamburg.autoservice.exception.handler.UserNotFoundException;
import ch.qos.logback.core.model.processor.ModelHandlerException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ProblemDetail;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import static org.springframework.http.HttpStatus.CONFLICT;
import static org.springframework.http.HttpStatus.NOT_FOUND;

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

}
