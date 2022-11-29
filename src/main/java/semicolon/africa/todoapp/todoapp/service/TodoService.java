package semicolon.africa.todoapp.todoapp.service;

import org.springframework.data.domain.Page;
import semicolon.africa.todoapp.todoapp.dao.request.CreateTodoRequest;
import semicolon.africa.todoapp.todoapp.dao.request.FindAllTodoRequest;
import semicolon.africa.todoapp.todoapp.dao.request.UpdateTodoRequest;
import semicolon.africa.todoapp.todoapp.dto.model.Todo;
import semicolon.africa.todoapp.todoapp.exception.TodoCollecttionException;

public interface TodoService {
    Todo createTodo(CreateTodoRequest createTodoRequest);
    long sizeOfTodo();

    void deleteAll();


    Todo findTodoById(Long todoId) throws TodoCollecttionException;

    Page<Todo> findAllTodo(FindAllTodoRequest findAllTodoRequest);


    void deleteById(Long todoId) throws TodoCollecttionException;

    Todo updateTodo(UpdateTodoRequest updateTodoRequest, Long todoId) throws TodoCollecttionException;

    Todo findByTodo(String todo) throws TodoCollecttionException;
}
