package semicolon.africa.todoapp.todoapp.exception;

public class TodoCollecttionException extends Throwable {
    public TodoCollecttionException(String message) {
        super(message);
    }

    public static String notFoundExeception(Long id){

        return "Todo with " + id +" not found";
    }

    public static String TodoNotFoundExeception(String todo){

        return "Todo with " + todo +" not found";
    }

    public static  String TodoAlreadyExist(){
        return "Todo with given name already exists";
    }
}


