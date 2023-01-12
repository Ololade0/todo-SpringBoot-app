package semicolon.africa.todoapp.todoapp.exception;


import lombok.Getter;
import lombok.Setter;
import lombok.extern.slf4j.Slf4j;


@Slf4j
@Setter
@Getter
public class UserAlreadyExistException extends RuntimeException {
    private int statusCode;
    public UserAlreadyExistException(String email) {
        super(email);
    }

    public static String userAlreadyExistExecption(String email) {

        return "User with " + email + " already exist";
    }

}
