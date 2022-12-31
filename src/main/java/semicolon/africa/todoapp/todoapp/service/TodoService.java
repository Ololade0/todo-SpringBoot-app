package semicolon.africa.todoapp.todoapp.service;

import org.springframework.data.domain.Page;
import semicolon.africa.todoapp.todoapp.dao.request.CreateTodoRequest;
import semicolon.africa.todoapp.todoapp.dao.request.FindAllTodoRequest;
import semicolon.africa.todoapp.todoapp.dao.request.UpdateTodoRequest;
import semicolon.africa.todoapp.todoapp.dto.model.Todo;
import semicolon.africa.todoapp.todoapp.exception.TodoException;

import java.util.List;

public interface TodoService {
    Todo createTodo(CreateTodoRequest createTodoRequest);
    long sizeOfTodo();

    void deleteAll();


    Todo findTodoById(Long todoId) throws TodoException;

//    Page<Todo> findAllTodo(FindAllTodoRequest findAllTodoRequest);


    void deleteById(Long todoId) throws TodoException;

    Todo updateTodo(UpdateTodoRequest updateTodoRequest, Long todoId) throws TodoException;

    Todo findByTodo(String todo) throws TodoException;

    List<Todo> findAllTodos();
}
