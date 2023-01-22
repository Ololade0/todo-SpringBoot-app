package semicolon.africa.todoapp.todoapp.controller;

import com.mashape.unirest.http.exceptions.UnirestException;
import lombok.RequiredArgsConstructor;
import org.springframework.data.repository.query.Param;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;
import semicolon.africa.todoapp.todoapp.dao.model.AuthToken;
import semicolon.africa.todoapp.todoapp.dao.model.Todo;
import semicolon.africa.todoapp.todoapp.dao.model.User;
import semicolon.africa.todoapp.todoapp.dto.request.*;
import semicolon.africa.todoapp.todoapp.dto.response.CreateTodoResponse;
import semicolon.africa.todoapp.todoapp.dto.response.DeleteTodoResponse;
import semicolon.africa.todoapp.todoapp.dto.response.UpdateTodoResponse;
import semicolon.africa.todoapp.todoapp.dto.response.UpdateUserProfileResponse;
import semicolon.africa.todoapp.todoapp.exception.TodoException;
import semicolon.africa.todoapp.todoapp.exception.UserCannotBeFoundException;
import semicolon.africa.todoapp.todoapp.security.jwt.TokenProvider;
import semicolon.africa.todoapp.todoapp.service.UserService;

import java.util.List;

@RestController
@RequestMapping("/api/v1/auth")
@RequiredArgsConstructor
public class UserController {
    private final UserService userService;
    private final AuthenticationManager authenticationManager;
    private final TokenProvider tokenProvider;

    @PostMapping("/register")
    public ResponseEntity<?> registerUser(@RequestBody User registerUser) throws UnirestException {
        User registeredUser = userService.registerUser(registerUser);
        return new ResponseEntity<>(registeredUser, HttpStatus.CREATED);
    }

    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody UserLoginRequestModel loginRequest) throws UserCannotBeFoundException {
        Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(loginRequest.getEmail(),
                        loginRequest.getPassword())
        );
        SecurityContextHolder.getContext().setAuthentication(authentication);
              final String token = tokenProvider.generateJWTToken(authentication);
        User user = userService.findByEmail(loginRequest.getEmail());
        return new ResponseEntity<>(new AuthToken(token, user.getUserId()), HttpStatus.OK);
    }

    @GetMapping("{id}")
    public ResponseEntity<?> findUserById(@PathVariable Long id) throws UserCannotBeFoundException {
        User foundUser = userService.findUserById(id);
        return new ResponseEntity<>(foundUser, HttpStatus.ACCEPTED);
    }

    @GetMapping("/all-user")
    public ResponseEntity<?> findAllUsers() {
        return new ResponseEntity<>(userService.findAllUsers(), HttpStatus.ACCEPTED);
    }

    @DeleteMapping("{id}/delete")
    public ResponseEntity<?> deleteUserById(@PathVariable Long id) throws UserCannotBeFoundException {
        String deletedUsers = userService.deleteById(id);
        return new ResponseEntity<>(deletedUsers, HttpStatus.ACCEPTED);
    }

    @DeleteMapping("deleteuser")
    public ResponseEntity<?> deleteAllUser()  {
        String deletedUsers = userService.deleteAllUsers();
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



    @GetMapping("/all-todo")
    public ResponseEntity<?> findAllTodos() {
        return new ResponseEntity<>(userService.findAllTodos(), HttpStatus.ACCEPTED);
    }

    @DeleteMapping("/delete-todo")
    public ResponseEntity<?> deleteTodoById(DeleteTodoIdRequest deleteTodoIdRequest) throws UserCannotBeFoundException {
        DeleteTodoResponse deletedUsers = userService.deleteToDoById(deleteTodoIdRequest);
        return new ResponseEntity<>(deletedUsers, HttpStatus.ACCEPTED);
    }

    @PutMapping("{id}/profile-todo")
    public ResponseEntity<?> updateTodo(@RequestBody UpdateTodoRequest updateTodoRequest, @PathVariable Long id) throws UserCannotBeFoundException {
        UpdateTodoResponse updateTodo = userService.updateTodo(updateTodoRequest, id);
        return new ResponseEntity<>(updateTodo, HttpStatus.ACCEPTED);

    }

    @DeleteMapping("/delete-alltodo")
    public ResponseEntity<?> deleteAllTodo() {
        try {
            String deletedSchools = userService.deleteAllTodo();
            return new ResponseEntity<>(deletedSchools, HttpStatus.ACCEPTED);
        } catch (TodoException e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
        }

    }

    @GetMapping()
    public ResponseEntity<?> getUsersByFirstName(@Param(value = "firstName") String firstName) throws UserCannotBeFoundException {
        List<User> foundUser = userService.findUserByFirstName(firstName);
        return new ResponseEntity<>(foundUser, HttpStatus.ACCEPTED);
    }


}
