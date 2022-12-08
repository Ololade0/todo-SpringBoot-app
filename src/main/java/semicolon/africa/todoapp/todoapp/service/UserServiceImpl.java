package semicolon.africa.todoapp.todoapp.service;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;

import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import semicolon.africa.todoapp.todoapp.dao.request.*;
import semicolon.africa.todoapp.todoapp.dao.response.*;
import semicolon.africa.todoapp.todoapp.dto.model.Role;
import semicolon.africa.todoapp.todoapp.dto.model.Todo;
import semicolon.africa.todoapp.todoapp.dto.model.User;
import semicolon.africa.todoapp.todoapp.dto.repository.UserRepository;
import semicolon.africa.todoapp.todoapp.exception.TodoException;
import semicolon.africa.todoapp.todoapp.exception.UserCannotBeFoundException;

import java.util.Optional;

@Service
@Slf4j
@AllArgsConstructor
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;

    private final TodoService todoService;

    private final PasswordEncoder passwordEncoder;

    @Override
    public RegisterUserResponse registerUser(RegisterUserRequest registerUserRequest) {
        User user = User.builder()
                .firstName(registerUserRequest.getFirstName())
                .lastName(registerUserRequest.getLastName())
                .email(registerUserRequest.getEmail())
                .phoneNumber(registerUserRequest.getPhoneNumber())
               .password(passwordEncoder.encode(registerUserRequest.getPassword()))
                .role(Role.USER)
                .build();

        User registeredUser= userRepository.save(user);
        return RegisterUserResponse
                .builder()
                .message("User successfully registered")
                 .code(200)
                .userId(registeredUser.getUserId())
                .build();
    }

    @Override
    public long getTotalUsers() {
        return userRepository.count();
    }

    @Override
    public String deleteAllUsers() {
        userRepository.deleteAll();
        return "ALl Users successfully deleted";

    }

    @Override
    public User findUserById(Long userId) throws UserCannotBeFoundException {
      Optional<User> foundUser =  userRepository.findById(userId);
      if(foundUser.isEmpty()){
          throw new UserCannotBeFoundException(UserCannotBeFoundException.notFoundExeception(userId));
      }
        return foundUser.get();
    }
    @Override
    public Page<User> findAllUsers(FindAllUserRequest findAllUserRequest) {
        Pageable pageable = PageRequest.of(findAllUserRequest.getPageNumber()-1, findAllUserRequest.getNumberOfPerPages());
        return userRepository.findAll(pageable);
    }

    @Override
    public String deleteById(Long userId) throws UserCannotBeFoundException {
       Optional<User> foundUser = userRepository.findById(userId);
       if(foundUser.isPresent()){
           userRepository.deleteById(userId);
           return "User successfully deleted";

       }
      else {
           throw new UserCannotBeFoundException(UserCannotBeFoundException.notFoundExeception(userId));

      }



    }

    @Override
    public UpdateUserProfileResponse updateUserProfile(UpdateUserProfileRequest updateUserProfileRequest) throws UserCannotBeFoundException {
        Optional<User> foundUser = userRepository.findById(updateUserProfileRequest.getUserId());
        if(foundUser.isEmpty()) {
            throw new UserCannotBeFoundException(UserCannotBeFoundException.notFoundExeception(null));
        }
        else {
            if(updateUserProfileRequest.getFirstName()!= null){
                foundUser.get().setFirstName(updateUserProfileRequest.getFirstName());
            }
            if(updateUserProfileRequest.getLastName()!= null){
                foundUser.get().setLastName(updateUserProfileRequest.getLastName());
            }
            if(updateUserProfileRequest.getEmail()!= null){
                foundUser.get().setEmail(updateUserProfileRequest.getEmail());
            }
            if(updateUserProfileRequest.getPhoneNumber()!= null){
                foundUser.get().setPhoneNumber(updateUserProfileRequest.getPhoneNumber());
            }
            userRepository.save(foundUser.get());

        }
        return UpdateUserProfileResponse
                .builder()
                .message("Profile successfully updated")
                .id(foundUser.get().getUserId())
                .build();

    }

    @Override
    public CreateTodoResponse createTodo(CreateTodoRequest createTodoRequest)  {
        Todo foundTodo = todoService.createTodo(createTodoRequest);
        Optional<User> foundUser = userRepository.findById(createTodoRequest.getUserId());
        if(foundUser.isPresent()){
            foundUser.get().getTodoList().add(foundTodo);
           userRepository.save(foundUser.get());
        }
        return CreateTodoResponse
                .builder()
                .message("Todo successfully created")
                .id(foundTodo.getTodoId())
                .code(200)
                .build();
    }

    @Override
    public Todo findTodoById(FindTodoByIdRequest findTodoByIdRequest) throws TodoException, UserCannotBeFoundException {
        Optional<User> foundUser = userRepository.findById(findTodoByIdRequest.getUserId());
        if(foundUser.isPresent()){
           return todoService.findTodoById(findTodoByIdRequest.getTodoId());
        }
        else {
            throw new UserCannotBeFoundException(UserCannotBeFoundException.notFoundExeception(findTodoByIdRequest.getUserId()));
        }

    }

    @Override
    public Page<Todo> findAllTodo(FindAllTodoRequest findAllTodoRequest) throws UserCannotBeFoundException {
        Optional<User> foundUser = userRepository.findById(findAllTodoRequest.getUserId());
        if(foundUser.isPresent()){
            return todoService.findAllTodo(findAllTodoRequest);
        }
        else {
            throw new UserCannotBeFoundException(UserCannotBeFoundException.notFoundExeception(findAllTodoRequest.getUserId()));
        }
    }

    @Override
    public String deleteAllTodo(DeleteTodoRequest deleteTodoRequest) {
        Optional<User> foundUser = userRepository.findById(deleteTodoRequest.getUserId());
        if(foundUser.isPresent())
            todoService.deleteAll();
         return "Todo successfully deleted";

    }

    @Override
    public long getTotaalTodo() {
        return todoService.sizeOfTodo();
    }

    @Override
    public void deleteAll() {
        todoService.deleteAll();

    }

    @Override
    public DeleteTodoResponse deleteToDoById(DeleteTodoIdRequest deleteTodoIdRequest) throws TodoException, UserCannotBeFoundException {
        log.info("request --> {}", deleteTodoIdRequest);
        User user = userRepository.findUserByUserId(deleteTodoIdRequest.getUserId());
        if(user!=null) {
            todoService.deleteById(deleteTodoIdRequest.getTodoId());
        }
        else {
            throw new UserCannotBeFoundException(UserCannotBeFoundException.notFoundExeception(deleteTodoIdRequest.getUserId()));
        }
        return DeleteTodoResponse.builder()
                .message("Todo successfully deleted")
                .build();


            }


    @Override
    public UpdateTodoResponse updateTodo(UpdateTodoRequest updateTodoRequest, Long id) throws TodoException, UserCannotBeFoundException {
      Todo foundTodo =  todoService.updateTodo(updateTodoRequest, id);
       User foundUser =  userRepository.findUserByUserId(updateTodoRequest.getUserId());
       if(foundUser == null){
           throw new UserCannotBeFoundException(UserCannotBeFoundException.notFoundExeception(id));
       }
       else{
            foundUser.getTodoList().add(foundTodo);
            userRepository.save(foundUser);
        }
        return UpdateTodoResponse
                .builder()
                .message("Todo successfully updated")
                .id(foundTodo.getTodoId())
                .todo(foundTodo.getTodo())
                .build();

    }

    @Override
    public User findByEmail(String username) {
        return userRepository.findByEmail(username);
    }



}







