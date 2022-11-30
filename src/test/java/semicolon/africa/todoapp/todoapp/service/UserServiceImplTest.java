package semicolon.africa.todoapp.todoapp.service;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Page;
import semicolon.africa.todoapp.todoapp.dao.request.*;
import semicolon.africa.todoapp.todoapp.dao.response.CreateTodoResponse;
import semicolon.africa.todoapp.todoapp.dao.response.RegisterUserResponse;
import semicolon.africa.todoapp.todoapp.dao.response.UpdateTodoResponse;
import semicolon.africa.todoapp.todoapp.dao.response.UpdateUserProfileResponse;
import semicolon.africa.todoapp.todoapp.dto.model.Todo;
import semicolon.africa.todoapp.todoapp.dto.model.User;
import semicolon.africa.todoapp.todoapp.exception.TodoCollecttionException;
import semicolon.africa.todoapp.todoapp.exception.UserCannotBeFoundException;

import java.util.Date;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;
@SpringBootTest
class UserServiceImplTest {
    RegisterUserResponse registeredUser;
    CreateTodoResponse response;
    @Autowired
    private UserService userService;


    @BeforeEach
    void setUp() throws UserCannotBeFoundException {
        RegisterUserRequest registerUserRequest = RegisterUserRequest
                .builder()
                .firstName("Ololade")
                .lastName("Oluwatosin")
                .email("adesuyiololade@gmail.com")
                .phoneNumber("08109093828")
                .build();
         registeredUser =  userService.registerUser(registerUserRequest);

        CreateTodoRequest createTodoRequest = CreateTodoRequest
                .builder()
                .userId(registeredUser.getUserId())
                .todo("My Todo")
                .description("My description")
                .isCompleted(true)
                .createdAt(new Date(System.currentTimeMillis()))
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
        RegisterUserRequest registerUserRequest = RegisterUserRequest
                .builder()
                .firstName("Ololade")
                .lastName("Oluwatosin")
                .email("adesuyiololade@gmail.com")
                .phoneNumber("08109093828")
                .build();
      registeredUser =  userService.registerUser(registerUserRequest);
        assertEquals(2L, userService.getTotalUsers());
        assertThat(registeredUser.getUserId()).isNotNull();
        assertEquals(200, registeredUser.getCode());

    }
    @Test
    public void userCanBeFindById() throws UserCannotBeFoundException {
       User foundUser =  userService.findUserById(registeredUser.getUserId());
        assertThat(foundUser.getUserId()).isNotNull();
        assertThat(foundUser.getUserId()).isEqualTo(registeredUser.getUserId());
    }
    @Test
    public void allUserCanBeFound(){
        FindAllUserRequest findAllUserRequest = FindAllUserRequest
                .builder()
                .numberOfPerPages(8)
                .pageNumber(1)
                .build();
       Page<User> userPage = userService.findAllUsers(findAllUserRequest);
        assertThat(userPage).isNotNull();
        assertThat(userPage.getTotalElements()).isGreaterThan(0);
    }
    @Test
    public void allUserCanBeDeleted(){
        userService.deleteAllUsers();
        assertEquals(0, userService.getTotalUsers());
    }

    @Test
    public void usersCanBeDeletedById() throws UserCannotBeFoundException {
        userService.deleteById(registeredUser.getUserId());
        assertEquals(0, userService.getTotalUsers());
    }

    @Test
    public void usersProfileCanBeUpdated() throws UserCannotBeFoundException {
        UpdateUserProfileRequest updateUserProfileRequest = UpdateUserProfileRequest
                .builder()
                .email("jummy@gmail.com")
                .firstName("Iseoluwa")
                .lastName("Pelumi")
                .phoneNumber("09031807593")
                .build();
      UpdateUserProfileResponse updateUserProfileResponse = userService.updateUserProfile(updateUserProfileRequest, registeredUser.getUserId());
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
                .createdAt(new Date(System.currentTimeMillis()))
                .build();
       CreateTodoResponse response = userService.createTodo(createTodoRequest);
        assertThat(response.getId()).isNotNull();
        assertEquals(200, response.getCode());
        assertEquals("Todo successfully created", response.getMessage());
    }
    @Test
    public void UserCanFindTodoById() throws TodoCollecttionException, UserCannotBeFoundException {
        FindTodoByIdRequest findTodoByIdRequest = FindTodoByIdRequest.builder()
                        .todoId(response.getId())
                        .UserId(registeredUser.getUserId())
                        .build();
    Todo foundTodo =     userService.findTodoById(findTodoByIdRequest);
    assertThat(foundTodo.getTodoId()).isNotNull();

    }

    @Test
    public void UserCanFindAllTodo() throws UserCannotBeFoundException {
        FindAllTodoRequest findAllTodoRequest = FindAllTodoRequest
                .builder()
                .userId(registeredUser.getUserId())
                .numberOfPerPages(1)
                .pageNumber(1)
                .build();
       Page<Todo> page = userService.findAllTodo(findAllTodoRequest);
        assertThat(page.getTotalElements()).isNotNull();
       assertEquals(1L, userService.findAllTodo(findAllTodoRequest).getTotalElements());
    }

    @Test
    public void UserCanDeleteAllTodo(){
        DeleteTodoRequest deleteTodoRequest = DeleteTodoRequest.builder()
                .userId(registeredUser.getUserId())
                .todoId(response.getId())
                .build();
        userService.deleteAllTodo(deleteTodoRequest);
        assertEquals(0, userService.getTotaalTodo());
    }
    @Test
    public void UserCanDeletedById() throws TodoCollecttionException, UserCannotBeFoundException {
        DeleteTodoIdRequest deleteTodoIdRequest = DeleteTodoIdRequest
                .builder()
                .todoId(response.getId())
                .userId(registeredUser.getUserId())
                .build();
        userService.deleteToDoById(deleteTodoIdRequest);

        assertEquals(0, userService.getTotaalTodo());
    }

    @Test
    public void userCanUpdateTodo() throws TodoCollecttionException {
        UpdateTodoRequest updateTodoRequest = UpdateTodoRequest
                .builder()
                .todo("Ololade's Todo")
                .userId(registeredUser.getUserId())
                .description("Ololades Description")
                .isCompleted(false)
                .build();
    UpdateTodoResponse updateUserProfileResponse= userService.updateTodoss(updateTodoRequest,response.getId());
        assertThat(updateUserProfileResponse.getId()).isNotNull();
        assertEquals("Todo successfully updated", updateUserProfileResponse.getMessage());
       assertEquals("Ololade's Todo", updateUserProfileResponse.getTodo());
        System.out.println(updateUserProfileResponse);
    }



}