package semicolon.africa.todoapp.todoapp.service;

import semicolon.africa.todoapp.todoapp.dao.model.Todo;
import semicolon.africa.todoapp.todoapp.dao.model.User;
import semicolon.africa.todoapp.todoapp.dto.request.CreateTodoRequest;
import semicolon.africa.todoapp.todoapp.dto.request.UpdateTodoRequest;
import semicolon.africa.todoapp.todoapp.exception.TodoException;

import java.util.List;

public interface TodoService {
    Todo createTodo(CreateTodoRequest createTodoRequest);
    long sizeOfTodo();

    void deleteAll();


    Todo findTodoById(Long todoId) throws TodoException;

    void deleteById(Long todoId) throws TodoException;

    Todo updateTodo(UpdateTodoRequest updateTodoRequest, Long todoId) throws TodoException;

    Todo findByTodo(String todo) throws TodoException;

    List<Todo> findAllTodos();



}
