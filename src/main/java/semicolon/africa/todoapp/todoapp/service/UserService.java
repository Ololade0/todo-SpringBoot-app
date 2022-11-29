package semicolon.africa.todoapp.todoapp.service;

import org.springframework.data.domain.Page;
import semicolon.africa.todoapp.todoapp.dao.request.*;
import semicolon.africa.todoapp.todoapp.dao.response.CreateTodoResponse;
import semicolon.africa.todoapp.todoapp.dao.response.DeleteCourseResponse;
import semicolon.africa.todoapp.todoapp.dao.response.RegisterUserResponse;
import semicolon.africa.todoapp.todoapp.dao.response.UpdateUserProfileResponse;
import semicolon.africa.todoapp.todoapp.dto.model.Todo;
import semicolon.africa.todoapp.todoapp.dto.model.User;
import semicolon.africa.todoapp.todoapp.exception.TodoCollecttionException;
import semicolon.africa.todoapp.todoapp.exception.UserCannotBeFoundException;

public interface UserService {
    RegisterUserResponse registerUser(RegisterUserRequest registerUserRequest);

    long getTotalUsers();

    void deleteAllUsers();

    User findUserById(Long userId) throws UserCannotBeFoundException;

    Page<User> findAllUsers(FindAllUserRequest findAllUserRequest);

    void deleteById(Long userId) throws UserCannotBeFoundException;

    UpdateUserProfileResponse updateUserProfile(UpdateUserProfileRequest updateUserProfileRequest, Long userId) throws UserCannotBeFoundException;

    CreateTodoResponse createTodo(CreateTodoRequest createTodoRequest) throws UserCannotBeFoundException;


    Todo findTodoById(FindTodoByIdRequest findTodoByIdRequest) throws TodoCollecttionException, UserCannotBeFoundException;

    Page<Todo> findAllTodo(FindAllTodoRequest findAllTodoRequest) throws UserCannotBeFoundException;

    String deleteAllTodo(DeleteTodoRequest deleteTodoRequest);

     long getTotaalTodo();

    void deleteAll();


    DeleteCourseResponse deleteToDoById(DeleteTodoIdRequest deleteTodoIdRequest) throws TodoCollecttionException;
}
