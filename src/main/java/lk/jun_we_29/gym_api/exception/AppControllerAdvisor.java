package lk.jun_we_29.gym_api.exception;

import lk.jun_we_29.gym_api.controller.dto.request.ErrorMessageDTO;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class AppControllerAdvisor {

    @ResponseStatus(HttpStatus.NOT_FOUND)
    @ExceptionHandler({
            UserNotFoundException.class,
            WorkoutStatusNotFoundException.class
    })
    public ErrorMessageDTO handelException(Exception exception){

        ErrorMessageDTO errorMessage = new ErrorMessageDTO();
        errorMessage.setMessage(exception.getMessage());

        return errorMessage;


    }
}
