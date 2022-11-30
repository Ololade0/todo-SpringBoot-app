package semicolon.africa.todoapp.todoapp.exception;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class UserCannotBeFoundException extends Throwable {
    public UserCannotBeFoundException(String message) {
        super(message);
    }

    public static String notFoundExeception(Long id) {

        return "User with " + id + " not found";
    }

    public static String notFoundException(String email) {

        return "User with " + email + " not found";
    }

    public static String TodoAlreadyExist() {
        return "Todo with given name already exists";
    }


}


