package semicolon.africa.todoapp.todoapp.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import semicolon.africa.todoapp.todoapp.dao.model.Todo;
import semicolon.africa.todoapp.todoapp.dto.request.CreateTodoRequest;


import semicolon.africa.todoapp.todoapp.service.TodoService;

import java.util.List;

@RestController
@RequestMapping("/api/v1/auth")
@RequiredArgsConstructor
public class TodoController {
    private TodoService todoService;

//    @PreAuthorize("hasRole('ROLE_USER')")
    @PostMapping("/too")
    public ResponseEntity<?> createTodo(@RequestBody CreateTodoRequest createTodoRequest){
        Todo createdTodo = todoService.createTodo(createTodoRequest);
        return new ResponseEntity<>(createdTodo, HttpStatus.CREATED);
    }

//    @GetMapping("/user/{firstName}")
//    public List<Todo> getTodosByUserName(@PathVariable String firstName) {
//        return todoService.findTodoByUser(firstName);
//    }
}



