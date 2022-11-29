package semicolon.africa.todoapp.todoapp.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import semicolon.africa.todoapp.todoapp.dao.request.*;
import semicolon.africa.todoapp.todoapp.dao.response.CreateTodoResponse;
import semicolon.africa.todoapp.todoapp.dao.response.RegisterUserResponse;
import semicolon.africa.todoapp.todoapp.dao.response.UpdateUserProfileResponse;
import semicolon.africa.todoapp.todoapp.dto.model.Todo;
import semicolon.africa.todoapp.todoapp.dto.model.User;
import semicolon.africa.todoapp.todoapp.dto.repository.UserRepository;
import semicolon.africa.todoapp.todoapp.exception.TodoCollecttionException;
import semicolon.africa.todoapp.todoapp.exception.UserCannotBeFoundException;

import java.util.Optional;

@Service
public class UserServiceImpl implements UserService {
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private TodoService todoService;
    @Override
    public RegisterUserResponse registerUser(RegisterUserRequest registerUserRequest) {
        User user = User.builder()
                .firstName(registerUserRequest.getFirstName())
                .lastName(registerUserRequest.getLastName())
                .email(registerUserRequest.getEmail())
                .phoneNumber(registerUserRequest.getPhoneNumber())
                .build();
        User registeredUser = userRepository.save(user);

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
    public void deleteAllUsers() {
        userRepository.deleteAll();

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
    public void deleteById(Long userId) throws UserCannotBeFoundException {
       Optional<User> foundUser = userRepository.findById(userId);
       if(foundUser.isPresent()){
           userRepository.deleteById(userId);

       }
      else {
           throw new UserCannotBeFoundException(UserCannotBeFoundException.notFoundExeception(userId));

      }



    }

    @Override
    public UpdateUserProfileResponse updateUserProfile(UpdateUserProfileRequest updateUserProfileRequest, Long userId) throws UserCannotBeFoundException {
        Optional<User> foundUser = userRepository.findById(userId);
        if(foundUser.isEmpty()) {
            throw new UserCannotBeFoundException(UserCannotBeFoundException.notFoundExeception(userId));
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
        UpdateUserProfileResponse updateUserProfileResponse = UpdateUserProfileResponse
                .builder()
                .message("Profile successfully updated")
                .id(foundUser.get().getUserId())
                .build();
        return updateUserProfileResponse;

    }

    @Override
    public CreateTodoResponse createTodo(CreateTodoRequest createTodoRequest) throws UserCannotBeFoundException {
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
    public Todo findTodoById(FindTodoByIdRequest findTodoByIdRequest) throws TodoCollecttionException, UserCannotBeFoundException {
        Optional<User> foundUser = userRepository.findById(findTodoByIdRequest.getUserId());
        if(foundUser.isPresent()){
           return todoService.findTodoById(findTodoByIdRequest.getTodoId());
        }
        else {
            throw new UserCannotBeFoundException(UserCannotBeFoundException.notFoundExeception(findTodoByIdRequest.getUserId()));
        }

    }


}
