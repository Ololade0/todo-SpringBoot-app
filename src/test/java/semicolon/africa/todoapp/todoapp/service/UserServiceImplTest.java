package semicolon.africa.todoapp.todoapp.service;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import org.springframework.security.crypto.password.PasswordEncoder;
import semicolon.africa.todoapp.todoapp.dao.model.Todo;
import semicolon.africa.todoapp.todoapp.dao.model.User;
import semicolon.africa.todoapp.todoapp.dto.request.*;
import semicolon.africa.todoapp.todoapp.dto.response.CreateTodoResponse;
import semicolon.africa.todoapp.todoapp.dto.response.UpdateTodoResponse;
import semicolon.africa.todoapp.todoapp.dto.response.UpdateUserProfileResponse;
import semicolon.africa.todoapp.todoapp.exception.TodoException;
import semicolon.africa.todoapp.todoapp.exception.UserCannotBeFoundException;

import java.util.Date;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;
@SpringBootTest
class UserServiceImplTest {
    User registeredUser;
    CreateTodoResponse response;
    @Autowired
    private UserService userService;



    @BeforeEach
    void setUp() throws UserCannotBeFoundException {

        User registerUserRequest = User
                .builder()
                .firstName("Ololade")
                .lastName("Oluwatosin")
                .email("adesuyiololad@gmail.com")
               .password("1234")
                .phoneNumber("08109093828")
                .build();
         registeredUser =  userService.registerUser(registerUserRequest);


        CreateTodoRequest createTodoRequest = CreateTodoRequest
                .builder()
                .userId(registeredUser.getUserId())
                .todo("My Todo")
                .description("My description")
                .isCompleted(true)
                               .build();
         response = userService.createTodo(createTodoRequest);
    }


    @AfterEach
    void tearDown() {
        userService.deleteAllUsers();
        userService.deleteAll();
    }

    @Test
    public void userClassExist(){
        User user = new User();
        user.setEmail("adesuyiololade@gmail.com");
        user.setFirstName("Ololade");
        user.setLastName("Oluwatosin");
        user.setPhoneNumber("08109093828");
        assertEquals("adesuyiololade@gmail.com", user.getEmail());

    }
    @Test
    public void userCanBeRegistered(){
        User registerUserRequest = User
                .builder()
                .firstName("Ololade")
                .lastName("Oluwatosin")
                .email("adesuyiololade@gmail.com")
                .password("1234")
                .phoneNumber("08109093828")
                .build();
        registeredUser =  userService.registerUser(registerUserRequest);
        assertEquals(2L, userService.getTotalUsers());
        assertThat(registeredUser.getUserId()).isNotNull();



    }

    @Test
    public void userCanBeFindByFirstName() throws UserCannotBeFoundException {
        List <User> foundUser =  userService.findUserByFirstName(registeredUser.getFirstName());
//        foundUser.add(new User());
        if (!foundUser.isEmpty()) {
            assertThat(foundUser.get(0).getFirstName()).isNotNull();
            assertThat(foundUser.get(0).getFirstName()).isEqualTo(registeredUser.getFirstName());
            System.out.println(foundUser);
        }

    }

    @Test
    public void userCanBeFindById() throws UserCannotBeFoundException {
       User foundUser =  userService.findUserById(registeredUser.getUserId());
        assertThat(foundUser.getUserId()).isNotNull();
        assertThat(foundUser.getUserId()).isEqualTo(registeredUser.getUserId());
    }
    @Test
    public void allUserCanBeFound(){
        List<User> userPage = userService.findAllUsers();
        assertThat(userPage).isNotNull();
      }
    @Test
    public void allUserCanBeDeleted(){
        userService.deleteAllUsers();
        assertEquals(0, userService.getTotalUsers());
    }

    @Test
    public void usersCanBeDeletedById() throws UserCannotBeFoundException {
        userService.deleteById(registeredUser.getUserId());
          }

    @Test
    public void usersProfileCanBeUpdated() throws UserCannotBeFoundException {
        UpdateUserProfileRequest updateUserProfileRequest = UpdateUserProfileRequest
                .builder()
                .userId(registeredUser.getUserId())
                .email("jummy@gmail.com")
                .firstName("Iseoluwa")
                .lastName("Pelumi")
                .phoneNumber("09031807593")
                .build();
      UpdateUserProfileResponse updateUserProfileResponse = userService.updateUserProfile(updateUserProfileRequest);
        assertThat(updateUserProfileResponse.getId()).isNotNull();
        assertEquals("Profile successfully updated", updateUserProfileResponse.getMessage());

    }

    @Test
    public void UserCanCreateTodo() throws UserCannotBeFoundException {
        CreateTodoRequest createTodoRequest = CreateTodoRequest
                .builder()
                .userId(registeredUser.getUserId())
                .todo("My Todo")
                .description("My description")
                .isCompleted(true)
                .build();
       CreateTodoResponse response = userService.createTodo(createTodoRequest);
        assertThat(response.getId()).isNotNull();
        assertEquals(200, response.getCode());
        assertEquals("Todo successfully created", response.getMessage());
    }
    @Test
    public void UserCanFindTodoById() throws TodoException, UserCannotBeFoundException {
        FindTodoByIdRequest findTodoByIdRequest = FindTodoByIdRequest.builder()
                        .todoId(response.getId())
                        .UserId(registeredUser.getUserId())
                        .build();
    Todo foundTodo =     userService.findTodoById(findTodoByIdRequest);
    assertThat(foundTodo.getTodoId()).isNotNull();

    }

    @Test
    public void UserCanFindAllTodo() throws UserCannotBeFoundException {
       List<Todo> foundTodo =  userService.findAllTodos();
        assertEquals("My Todo",foundTodo.get(0).getTodo());

    }

    @Test
    public void UserCanDeleteAllTodo(){

        userService.deleteAllTodo();
        assertEquals(0, userService.getTotaalTodo());
    }
    @Test
    public void UserCanDeletedById() throws TodoException, UserCannotBeFoundException {
        DeleteTodoIdRequest deleteTodoIdRequest = DeleteTodoIdRequest
                .builder()
                .todoId(response.getId())
                .userId(registeredUser.getUserId())
                .build();
        userService.deleteToDoById(deleteTodoIdRequest);

        assertEquals(0, userService.getTotaalTodo());
    }

    @Test
    public void userCanUpdateTodo() throws TodoException, UserCannotBeFoundException {
        UpdateTodoRequest updateTodoRequest = UpdateTodoRequest
                .builder()
                .todo("Ololade's Todo")
                .userId(registeredUser.getUserId())
                .description("Ololades Description")
                .isCompleted(false)
                .build();
    UpdateTodoResponse updateUserProfileResponse= userService.updateTodo(updateTodoRequest,response.getId());
        assertThat(updateUserProfileResponse.getId()).isNotNull();
        assertEquals("Todo successfully updated", updateUserProfileResponse.getMessage());
       assertEquals("Ololade's Todo", updateUserProfileResponse.getTodo());
        System.out.println(updateUserProfileResponse);
    }



}