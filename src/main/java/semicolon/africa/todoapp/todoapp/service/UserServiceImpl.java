package semicolon.africa.todoapp.todoapp.service;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import semicolon.africa.todoapp.todoapp.dao.model.Role;
import semicolon.africa.todoapp.todoapp.dao.model.RoleType;
import semicolon.africa.todoapp.todoapp.dao.model.Todo;
import semicolon.africa.todoapp.todoapp.dao.model.User;
import semicolon.africa.todoapp.todoapp.dao.repository.UserRepository;
import semicolon.africa.todoapp.todoapp.dto.request.*;
import semicolon.africa.todoapp.todoapp.dto.response.*;
import semicolon.africa.todoapp.todoapp.exception.TodoException;
import semicolon.africa.todoapp.todoapp.exception.UserCannotBeFoundException;

import java.util.*;
import java.util.stream.Collectors;

@Service
@Slf4j
@AllArgsConstructor
public class UserServiceImpl implements UserService, UserDetailsService {

    private final UserRepository userRepository;

    private final TodoService todoService;

    private final PasswordEncoder passwordEncoder;

    @Override
    public User registerUser(User registerUser) {
//        User foundEmail = userRepository.findByEmail(registerUser.getEmail());
//        if (foundEmail != null) {
//            throw new UserAlreadyExistException(UserAlreadyExistException.userAlreadyExistExecption(registerUser.getEmail()));
//        }
        User user = User.builder()
                .firstName(registerUser.getFirstName())
                .lastName(registerUser.getLastName())
                .email(registerUser.getEmail())
                .phoneNumber(registerUser.getPhoneNumber())
                .password(passwordEncoder.encode(registerUser.getPassword()))
                .roles(new HashSet<>())
                .build();
        user.getRoles().add(new Role(RoleType.USER));
        return userRepository.save(user);


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
        Optional<User> foundUser = userRepository.findById(userId);
        if (foundUser.isEmpty()) {
            throw new UserCannotBeFoundException(UserCannotBeFoundException.notFoundExeception(userId));
        }
        return foundUser.get();
    }

    @Override
    public List<User> findAllUsers() {
        return userRepository.findAll();
    }

    @Override
    public String deleteById(Long userId) throws UserCannotBeFoundException {
        Optional<User> foundUser = userRepository.findById(userId);
        if (foundUser.isPresent()) {
            userRepository.deleteById(userId);
            return "User successfully deleted";

        } else {
            throw new UserCannotBeFoundException(UserCannotBeFoundException.notFoundExeception(userId));

        }


    }

    @Override
    public UpdateUserProfileResponse updateUserProfile(UpdateUserProfileRequest updateUserProfileRequest) throws UserCannotBeFoundException {
        Optional<User> foundUser = userRepository.findById(updateUserProfileRequest.getUserId());
        if (foundUser.isEmpty()) {
            throw new UserCannotBeFoundException(UserCannotBeFoundException.notFoundExeception(null));
        } else {
            if (updateUserProfileRequest.getFirstName() != null) {
                foundUser.get().setFirstName(updateUserProfileRequest.getFirstName());
            }
            if (updateUserProfileRequest.getLastName() != null) {
                foundUser.get().setLastName(updateUserProfileRequest.getLastName());
            }
            if (updateUserProfileRequest.getEmail() != null) {
                foundUser.get().setEmail(updateUserProfileRequest.getEmail());
            }
            if (updateUserProfileRequest.getPhoneNumber() != null) {
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
    public CreateTodoResponse createTodo(CreateTodoRequest createTodoRequest) {
        Todo foundTodo = todoService.createTodo(createTodoRequest);
        Optional<User> foundUser = userRepository.findById(createTodoRequest.getUserId());
        if (foundUser.isPresent()) {
            foundUser.get().getTodos().add(foundTodo);
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
        User foundUser = userRepository.findUserByUserId(findTodoByIdRequest.getUserId());
        if (foundUser != null) {
            return todoService.findTodoById(findTodoByIdRequest.getTodoId());
        } else {
            throw new UserCannotBeFoundException(UserCannotBeFoundException.notFoundExeception(findTodoByIdRequest.getUserId()));
        }

    }

    @Override
    public List<Todo> findAllTodos() {
        return todoService.findAllTodos();
    }

    @Override
    public UserLoginResponse login(UserLoginRequestModel userLoginRequestModel) {
        var user = userRepository.findByEmail(userLoginRequestModel.getEmail());
//        var user = userRepository.findByEmail(userLoginRequestModel.getEmail());
        if (user != null && user.getPassword().equals(userLoginRequestModel.getPassword())) ;
        return buildSuccessfulLoginResponse(user);
    }

    @Override
    public String deleteTodoByIds(Long id) {
        todoService.deleteById(id);
        return "All todo successfully deleted";
    }

    @Override
    public List<User> findUserByFirstName(String firstName) throws UserCannotBeFoundException {
     List <User>foundUser =   userRepository.findByFirstName(firstName);
     if(foundUser != null){
         return userRepository.findByFirstName(firstName);

     }
     throw new UserCannotBeFoundException("UserCannot be found");

    }




    private UserLoginResponse buildSuccessfulLoginResponse(User user) {
        return UserLoginResponse.builder()
//                .code(200)
                .message("Login successful")
                .build();

    }


    @Override
    public String deleteAllTodo() {
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
        if (user != null) {
            todoService.deleteById(deleteTodoIdRequest.getTodoId());
        } else {
            throw new UserCannotBeFoundException(UserCannotBeFoundException.notFoundExeception(deleteTodoIdRequest.getUserId()));
        }
        return DeleteTodoResponse.builder()
                .message("Todo successfully deleted")
                .build();


    }


    @Override
    public UpdateTodoResponse updateTodo(UpdateTodoRequest updateTodoRequest, Long id) throws TodoException, UserCannotBeFoundException {
        Todo foundTodo = todoService.updateTodo(updateTodoRequest, id);
        User foundUser = userRepository.findUserByUserId(updateTodoRequest.getUserId());
        if (foundUser == null) {
            throw new UserCannotBeFoundException(UserCannotBeFoundException.notFoundExeception(id));
        } else {
            foundUser.getTodos().add(foundTodo);
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

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = userRepository.findUserByEmail(username).orElse(null);
        if (user != null) {
            return new org.springframework.security.core.userdetails.User(user.getEmail(), user.getPassword(), getAuthorities(user.getRoles()));
        }
        throw new UsernameNotFoundException("User with email " + username + " does not exist");
    }

    private Collection<? extends GrantedAuthority> getAuthorities(Set<Role> roles) {
        return roles.stream().map(role -> new SimpleGrantedAuthority(role.getRoleType().name())).collect(Collectors.toSet());
    }
}










