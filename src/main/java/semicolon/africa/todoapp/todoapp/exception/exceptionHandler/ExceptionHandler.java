package semicolon.africa.todoapp.todoapp.exception.exceptionHandler;

import com.mashape.unirest.http.exceptions.UnirestException;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;
import semicolon.africa.todoapp.todoapp.exception.TodoException;
import semicolon.africa.todoapp.todoapp.exception.UserAlreadyExistException;
import semicolon.africa.todoapp.todoapp.exception.UserCannotBeFoundException;

@ControllerAdvice
public class ExceptionHandler extends ResponseEntityExceptionHandler {

    @org.springframework.web.bind.annotation.ExceptionHandler(UserCannotBeFoundException.class)
    public ResponseEntity<?> handleUserCannotBeFoundException(UserCannotBeFoundException userCannotBeFoundExcepttion){
        ApiError apiError = ApiError.builder()
                .message(userCannotBeFoundExcepttion.getMessage())
                .successful(false)
                .statusCode(userCannotBeFoundExcepttion.getStatusCode())
                .build();
        return new ResponseEntity<>(apiError, HttpStatus.valueOf(apiError.getStatusCode()));
    }


    @org.springframework.web.bind.annotation.ExceptionHandler(UserAlreadyExistException.class)
    public ResponseEntity<?> handleUserAlreadyExistException(UserAlreadyExistException userAlreadyExistException){
        ApiError apiError = ApiError.builder()
                .message(userAlreadyExistException.getMessage())
                .successful(false)
                .statusCode(userAlreadyExistException.getStatusCode())
                .build();
        return new ResponseEntity<>(apiError, HttpStatus.valueOf(apiError.getStatusCode()));
    }

    @org.springframework.web.bind.annotation.ExceptionHandler(UnirestException.class)
    public ResponseEntity<?> handleUnirestException( UnirestException unirestException){
        ApiError apiError = ApiError.builder()
                .message(unirestException.getMessage())
                .successful(false)
                .statusCode(400)
                .build();
        return new ResponseEntity<>(apiError, HttpStatus.valueOf(apiError.getStatusCode()));
    }

    @org.springframework.web.bind.annotation.ExceptionHandler(value = TodoException.class)
    public ResponseEntity<Object>handleTodoException(TodoException exception, WebRequest request){
        return new ResponseEntity<>(exception.getMessage(), new HttpHeaders(), HttpStatus.INTERNAL_SERVER_ERROR);
    }
}
