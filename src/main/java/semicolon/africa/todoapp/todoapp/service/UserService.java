package semicolon.africa.todoapp.todoapp.service;

import semicolon.africa.todoapp.todoapp.dao.model.Todo;
import semicolon.africa.todoapp.todoapp.dao.model.User;
import semicolon.africa.todoapp.todoapp.dto.request.*;
import semicolon.africa.todoapp.todoapp.dto.response.*;
import semicolon.africa.todoapp.todoapp.exception.TodoException;
import semicolon.africa.todoapp.todoapp.exception.UserCannotBeFoundException;

import java.util.List;

public interface UserService {
   User registerUser(User registerUser);

    long getTotalUsers();

    String deleteAllUsers();

    User findUserById(Long userId) throws UserCannotBeFoundException;

    List<User> findAllUsers();

    String deleteById(Long userId) throws UserCannotBeFoundException;

    UpdateUserProfileResponse updateUserProfile(UpdateUserProfileRequest updateUserProfileRequest) throws UserCannotBeFoundException;

    CreateTodoResponse createTodo(CreateTodoRequest createTodoRequest) throws UserCannotBeFoundException;


    Todo findTodoById(FindTodoByIdRequest findTodoByIdRequest) throws TodoException, UserCannotBeFoundException;



    String deleteAllTodo();

     long getTotaalTodo();

    void deleteAll();


    DeleteTodoResponse deleteToDoById(DeleteTodoIdRequest deleteTodoIdRequest) throws TodoException, UserCannotBeFoundException;

    UpdateTodoResponse updateTodo(UpdateTodoRequest updateTodoRequest, Long id) throws TodoException, UserCannotBeFoundException;

    User findByEmail(String username);

    List<Todo> findAllTodos();

    UserLoginResponse login(UserLoginRequestModel userLoginRequestModel);

 String deleteTodoByIds(Long id);
}
