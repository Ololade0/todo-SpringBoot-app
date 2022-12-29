package semicolon.africa.todoapp.todoapp.controller;

import lombok.AllArgsConstructor;
import org.springframework.core.env.Environment;
import org.springframework.core.io.ClassPathResource;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.web.bind.annotation.*;
import org.thymeleaf.TemplateEngine;
import org.thymeleaf.context.Context;
import semicolon.africa.todoapp.todoapp.dao.request.*;
import semicolon.africa.todoapp.todoapp.dao.response.*;
import semicolon.africa.todoapp.todoapp.dto.model.Todo;
import semicolon.africa.todoapp.todoapp.dto.model.User;
import semicolon.africa.todoapp.todoapp.exception.TodoException;
import semicolon.africa.todoapp.todoapp.exception.UserCannotBeFoundException;
import semicolon.africa.todoapp.todoapp.service.UserService;
import org.springframework.context.i18n.LocaleContextHolder;

import javax.mail.MessagingException;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

import java.io.UnsupportedEncodingException;
import java.util.HashMap;
import java.util.Map;

@RestController
@AllArgsConstructor
@RequestMapping("/api/v1/user")
public class UserController {
    private static final String TEMPLATE_NAME = "registration";
    private static final String SPRING_LOGO_IMAGE = "templates/images/Todo.jpg";
    private static final String PNG_MIME = "image/png";
    private static final String MAIL_SUBJECT = "Registration Confirmation";
    private final Environment environment;
    private final JavaMailSender mailSender;
    private final TemplateEngine htmlTemplateEngine;
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


    @GetMapping("/all-todo")
    public ResponseEntity<?> findAllTodo(@RequestBody FindAllTodoRequest findAllTodoRequest) throws UserCannotBeFoundException {
        return new ResponseEntity<>(userService.findAllTodo(findAllTodoRequest), HttpStatus.ACCEPTED);
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

    @PostMapping("/email")
    public ResponseEntity<Object> register(@RequestBody MailRequest user)
            throws MessagingException, UnsupportedEncodingException {
        String confirmationUrl = "generated_confirmation_url";
        String mailFrom = environment.getProperty("spring.mail.properties.mail.smtp.from");
        String mailFromName = environment.getProperty("mail.from.name", "Identity");
        final MimeMessage mimeMessage = this.mailSender.createMimeMessage();
        final MimeMessageHelper email;

        email = new MimeMessageHelper(mimeMessage, true, "UTF-8");
        email.setTo(user.getEmail());
        email.setSubject(MAIL_SUBJECT);
        email.setFrom(new InternetAddress(mailFrom, mailFromName));

        final Context ctx = new Context(LocaleContextHolder.getLocale());
        ctx.setVariable("email", user.getEmail());
        ctx.setVariable("name", user.getReceiver());
        ctx.setVariable("springLogo", SPRING_LOGO_IMAGE);
        ctx.setVariable("url", confirmationUrl);

        final String htmlContent = this.htmlTemplateEngine.process(TEMPLATE_NAME, ctx);
        email.setText(htmlContent, true);

        ClassPathResource clr = new ClassPathResource(SPRING_LOGO_IMAGE);
        email.addInline("springLogo", clr, PNG_MIME);
        mailSender.send(mimeMessage);

        Map<String, String> body = new HashMap<>();
        body.put("message", "Message successfully sent");

        return new ResponseEntity<>(body, HttpStatus.OK);
    }
}




