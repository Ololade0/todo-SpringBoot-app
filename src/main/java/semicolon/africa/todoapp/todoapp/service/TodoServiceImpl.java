package semicolon.africa.todoapp.todoapp.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import semicolon.africa.todoapp.todoapp.dao.model.Todo;
import semicolon.africa.todoapp.todoapp.dao.repository.TodoRepository;
import semicolon.africa.todoapp.todoapp.dto.request.CreateTodoRequest;
import semicolon.africa.todoapp.todoapp.dto.request.UpdateTodoRequest;
import semicolon.africa.todoapp.todoapp.exception.TodoException;

import java.util.Date;
import java.util.List;
import java.util.Optional;

@Service
public class TodoServiceImpl implements TodoService{
    @Autowired
    private TodoRepository todoRepository;
    @Override
    public Todo createTodo(CreateTodoRequest createTodoRequest) {
        Todo todo = Todo.builder()
                .todo(createTodoRequest.getTodo())
                .description(createTodoRequest.getDescription())
                .isCompleted(createTodoRequest.isCompleted())
                .build();
//         .createdAt(new Date(System.currentTimeMillis()))
        new Date(System.currentTimeMillis());
               return  todoRepository.save(todo);
    }


    @Override
    public Todo findTodoById(Long todoId) throws TodoException {
        Optional<Todo> todoDTOOptional = todoRepository.findById(todoId);
        if (todoDTOOptional.isEmpty()) {
            throw new TodoException(TodoException.notFoundExeception(todoId));

        } else {
            return todoDTOOptional.get();
        }
    }


    @Override
    public void deleteById(Long todoId) throws TodoException {
            Optional<Todo> todoDTOOptional = todoRepository.findById(todoId);
            if (todoDTOOptional.isEmpty()) {
                throw new TodoException(TodoException.notFoundExeception(todoId));

            } else {
                todoRepository.deleteById(todoId);
            }


    }

    @Override
    public Todo updateTodo(UpdateTodoRequest updateTodoRequest, Long todoId) throws TodoException {
        Optional<Todo> foundTodo = todoRepository.findById(todoId);
        if(foundTodo.isPresent()){
            if(updateTodoRequest.getTodo()!= null){
                foundTodo.get().setTodo(updateTodoRequest.getTodo());
            }
            if(updateTodoRequest.getDescription()!= null){
                foundTodo.get().setDescription(updateTodoRequest.getDescription());
            }
            if(updateTodoRequest.getIsCompleted()!=null){
                foundTodo.get().setIsCompleted(updateTodoRequest.getIsCompleted());
            }
            return todoRepository.save(foundTodo.get());

        }
        else {
            throw new TodoException(TodoException.notFoundExeception(todoId));
        }

    }

    @Override
    public Todo findByTodo(String todo) throws TodoException {
   Todo foundTodo =     todoRepository.findFirstByTodo(todo);
   if(todo != null){
       return foundTodo;
   }
        throw new TodoException(TodoException.TodoNotFoundExeception(null));
    }

    @Override
    public List<Todo> findAllTodos() {
       return todoRepository.findAll();
    }

    @Override
    public long sizeOfTodo() {
        return todoRepository.count();
    }

    @Override
    public void deleteAll() {
        todoRepository.deleteAll();

    }


}
