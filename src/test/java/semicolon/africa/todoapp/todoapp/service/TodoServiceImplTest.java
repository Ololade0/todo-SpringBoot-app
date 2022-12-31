package semicolon.africa.todoapp.todoapp.service;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Page;
import semicolon.africa.todoapp.todoapp.dao.request.CreateTodoRequest;
import semicolon.africa.todoapp.todoapp.dao.request.FindAllTodoRequest;
import semicolon.africa.todoapp.todoapp.dao.request.UpdateTodoRequest;
import semicolon.africa.todoapp.todoapp.dto.model.Todo;
import semicolon.africa.todoapp.todoapp.exception.TodoException;

import java.util.Date;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;
@SpringBootTest

class TodoServiceImplTest {
    Todo savedTodo;
    @Autowired
    private TodoService todoService;


    @BeforeEach
    void setUp() {
        CreateTodoRequest createTodoRequest = CreateTodoRequest
                .builder()
                .description("My description")
                .todo("My todo")
                .isCompleted(true)
                .createdAt(new Date(System.currentTimeMillis()))
                .build();
         savedTodo =  todoService.createTodo(createTodoRequest);

    }

    @AfterEach
    void tearDown() {
        todoService.deleteAll();
    }
    @Test
    public void todoClassExist(){
        Todo todo = new Todo();
        todo.setTodo("I love my Todo");
        todo.setIsCompleted(true);
        todo.setDescription("My todos Description");
        todo.setCreatedAt(new Date());
        assertEquals("I love my Todo", todo.getTodo());
    }
    @Test
    public void todoCanBeCreated(){
        CreateTodoRequest createTodoRequest = CreateTodoRequest
                .builder()
                .description("My description")
                .todo("My todo")
                .isCompleted(true)
                .createdAt(new Date(System.currentTimeMillis()))
                .build();
        savedTodo =  todoService.createTodo(createTodoRequest);
        assertEquals(2L, todoService.sizeOfTodo());
        assertEquals("My todo", savedTodo.getTodo());


    }
    @Test
    public void findTodoById() throws TodoException {
        Todo foundTodo = todoService.findTodoById(savedTodo.getTodoId());
        assertThat(foundTodo.getTodoId()).isEqualTo(savedTodo.getTodoId());
    }

    @Test
    public void findAllTodo() {
      List<Todo> foundTodo =  todoService.findAllTodos();
        assertEquals("My description", foundTodo.get(0).getDescription());
//        FindAllTodoRequest findAllTodoRequest = FindAllTodoRequest
//                .builder()
//             .numberOfPerPages(8)
//                .pageNumber(1)
//                .build();
//      Page<Todo> todoPage=  todoService.findAllTodo(findAllTodoRequest);
//        assertThat(todoPage).isNotNull();
//       assertThat(todoPage.getTotalElements()).isGreaterThan(0);
    }

    @Test
    public void allTodoCanBeDeleted(){
        todoService.deleteAll();
        assertEquals(0, todoService.sizeOfTodo());
    }

    @Test
    public void findTodoBYTodo() throws TodoException {
        Todo foundTodo =   todoService.findByTodo(savedTodo.getTodo());
        assertEquals("My todo", foundTodo.getTodo());
    }


    @Test
    public void deleteTodoById() throws TodoException {
        todoService.deleteById(savedTodo.getTodoId());
        assertEquals(0, todoService.sizeOfTodo());
    }

    @Test
    public void updateTodo() throws TodoException {
        FindAllTodoRequest findAllTodoRequest = FindAllTodoRequest
                .builder()
                .numberOfPerPages(8)
                .pageNumber(1)
                .build();
//        Page<Todo> todoPage=  todoService.findAllTodo(findAllTodoRequest);
        UpdateTodoRequest updateTodoRequest = UpdateTodoRequest
                .builder()
                .description("Ololades Description")
                .todo("Ololade Todo")
                .isCompleted(false)
                .build();
       Todo updatedTodo =  todoService.updateTodo(updateTodoRequest, savedTodo.getTodoId());
        assertEquals("Ololade Todo", updatedTodo.getTodo());
        assertEquals("Ololades Description", updatedTodo.getDescription());
        assertEquals(false, updatedTodo.getIsCompleted());
    }



}