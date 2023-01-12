package semicolon.africa.todoapp.todoapp.exception;

import lombok.Getter;
import lombok.Setter;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Setter
@Getter
public class UserCannotBeFoundException extends Throwable {
    private int statusCode;
    public UserCannotBeFoundException(String message) {
        super(message);
    }

    public UserCannotBeFoundException(String message,int statusCode) {
        super(message);
        this.statusCode = statusCode;
    }

    public static String notFoundExeception(Long id) {

        return "User with " + id + " not found";
    }




}


