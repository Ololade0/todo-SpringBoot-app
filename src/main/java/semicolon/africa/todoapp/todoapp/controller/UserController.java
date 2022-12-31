package semicolon.africa.todoapp.todoapp.controller;

import lombok.AllArgsConstructor;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import org.springframework.web.bind.annotation.*;

import semicolon.africa.todoapp.todoapp.dao.request.*;
import semicolon.africa.todoapp.todoapp.dao.response.*;
import semicolon.africa.todoapp.todoapp.dto.model.Todo;
import semicolon.africa.todoapp.todoapp.dto.model.User;
import semicolon.africa.todoapp.todoapp.exception.TodoException;
import semicolon.africa.todoapp.todoapp.exception.UserCannotBeFoundException;
import semicolon.africa.todoapp.todoapp.service.UserService;


@RestController
@AllArgsConstructor
@RequestMapping("/api/v1/user")
public class UserController {
    private final UserService userService;


    @PostMapping("/register")
    public ResponseEntity<?> registerUser(@RequestBody RegisterUserRequest userRequest) {
        RegisterUserResponse registerUserResponse = userService.registerUser(userRequest);
        return new ResponseEntity<>(registerUserResponse, HttpStatus.CREATED);
    }

    @GetMapping("{id}")
    public ResponseEntity<?> findUserById(@PathVariable Long id) throws UserCannotBeFoundException {
        User foundUser = userService.findUserById(id);
        return new ResponseEntity<>(foundUser, HttpStatus.ACCEPTED);
    }

    @GetMapping("/all-user")
    public ResponseEntity<?> findAllUsers(FindAllUserRequest findAllUserRequest) {
        return new ResponseEntity<>(userService.findAllUsers(findAllUserRequest), HttpStatus.ACCEPTED);
    }

    @DeleteMapping("/delete-all")
    public ResponseEntity<?> deleteAllUsers() {
        String deletedUser = userService.deleteAllUsers();
        return new ResponseEntity<>(deletedUser, HttpStatus.ACCEPTED);

    }


    @DeleteMapping("{id}/delete")
    public ResponseEntity<?> deleteUserById(@PathVariable Long id) throws UserCannotBeFoundException {
        String deletedUsers = userService.deleteById(id);
        return new ResponseEntity<>(deletedUsers, HttpStatus.ACCEPTED);
    }

    @PutMapping("/profile")
    public ResponseEntity<?> updateProfile(@RequestBody UpdateUserProfileRequest updateUserProfileRequest) throws UserCannotBeFoundException {
        UpdateUserProfileResponse updateUserProfile = userService.updateUserProfile(updateUserProfileRequest);
        return new ResponseEntity<>(updateUserProfile, HttpStatus.ACCEPTED);
    }

    @PostMapping("/todo")
    public ResponseEntity<?> createTodo(@RequestBody CreateTodoRequest createTodoRequest) throws UserCannotBeFoundException {
        CreateTodoResponse createTodoResponse = userService.createTodo(createTodoRequest);
        return new ResponseEntity<>(createTodoResponse, HttpStatus.CREATED);
    }


    @GetMapping("/id")
    public ResponseEntity<?> userCanFindTodoById(@RequestBody FindTodoByIdRequest findTodoByIdRequest) throws UserCannotBeFoundException {
        Todo foundTodo = userService.findTodoById(findTodoByIdRequest);
        return new ResponseEntity<>(foundTodo, HttpStatus.ACCEPTED);
    }


//    @GetMapping("/all-todo")
//    public ResponseEntity<?> findAllTodo(@RequestBody FindAllTodoRequest findAllTodoRequest) throws UserCannotBeFoundException {
//        return new ResponseEntity<>(userService.findAllTodo(findAllTodoRequest), HttpStatus.ACCEPTED);
//    }

    @GetMapping("/all-todo")
    public ResponseEntity<?> findAllTodos() throws UserCannotBeFoundException {
        return new ResponseEntity<>(userService.findAllTodos(), HttpStatus.ACCEPTED);
    }


    @DeleteMapping("/delete-todo")
    public ResponseEntity<?> deleteTodoById(@RequestBody DeleteTodoIdRequest deleteTodoIdRequest) throws UserCannotBeFoundException {
        DeleteTodoResponse deletedUsers = userService.deleteToDoById(deleteTodoIdRequest);
        return new ResponseEntity<>(deletedUsers, HttpStatus.ACCEPTED);
    }

    @DeleteMapping("/delete-alltodo")
    public ResponseEntity<?> deleteAllTodo(@RequestBody DeleteTodoRequest deleteTodoRequest) {
        try {
            String deletedSchools = userService.deleteAllTodo(deleteTodoRequest);
            return new ResponseEntity<>(deletedSchools, HttpStatus.ACCEPTED);
        } catch (TodoException e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
        }

    }
    @PutMapping("{id}/profile-todo")
    public ResponseEntity<?> updateTodo(@RequestBody UpdateTodoRequest updateTodoRequest, @PathVariable Long id) throws UserCannotBeFoundException {
           UpdateTodoResponse updateTodo = userService.updateTodo(updateTodoRequest, id);
            return new ResponseEntity<>(updateTodo, HttpStatus.ACCEPTED);

    }
        }





