package semicolon.africa.todoapp.todoapp.service;

import org.springframework.data.domain.Page;
import semicolon.africa.todoapp.todoapp.dao.request.*;
import semicolon.africa.todoapp.todoapp.dao.response.*;
import semicolon.africa.todoapp.todoapp.dto.model.Todo;
import semicolon.africa.todoapp.todoapp.dto.model.User;
import semicolon.africa.todoapp.todoapp.exception.TodoException;
import semicolon.africa.todoapp.todoapp.exception.UserCannotBeFoundException;

public interface UserService {
    RegisterUserResponse registerUser(RegisterUserRequest registerUserRequest);

    long getTotalUsers();

    String deleteAllUsers();

    User findUserById(Long userId) throws UserCannotBeFoundException;

    Page<User> findAllUsers(FindAllUserRequest findAllUserRequest);

    String deleteById(Long userId) throws UserCannotBeFoundException;

    UpdateUserProfileResponse updateUserProfile(UpdateUserProfileRequest updateUserProfileRequest) throws UserCannotBeFoundException;

    CreateTodoResponse createTodo(CreateTodoRequest createTodoRequest) throws UserCannotBeFoundException;


    Todo findTodoById(FindTodoByIdRequest findTodoByIdRequest) throws TodoException, UserCannotBeFoundException;

    Page<Todo> findAllTodo(FindAllTodoRequest findAllTodoRequest) throws UserCannotBeFoundException;

    String deleteAllTodo(DeleteTodoRequest deleteTodoRequest);

     long getTotaalTodo();

    void deleteAll();


    DeleteTodoResponse deleteToDoById(DeleteTodoIdRequest deleteTodoIdRequest) throws TodoException, UserCannotBeFoundException;

    UpdateTodoResponse updateTodo(UpdateTodoRequest updateTodoRequest, Long id) throws TodoException, UserCannotBeFoundException;

    User findByEmail(String username);

}
