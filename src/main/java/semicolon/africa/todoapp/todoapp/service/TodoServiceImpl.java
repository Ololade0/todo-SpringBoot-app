package semicolon.africa.todoapp.todoapp.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import semicolon.africa.todoapp.todoapp.dao.request.CreateTodoRequest;
import semicolon.africa.todoapp.todoapp.dao.request.FindAllTodoRequest;
import semicolon.africa.todoapp.todoapp.dao.request.UpdateTodoRequest;
import semicolon.africa.todoapp.todoapp.dto.model.Todo;
import semicolon.africa.todoapp.todoapp.dto.repository.TodoRepository;
import semicolon.africa.todoapp.todoapp.exception.TodoCollecttionException;
import java.util.Date;
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
                .createdAt(new Date(System.currentTimeMillis()))
                .build();
       return  todoRepository.save(todo);
    }


    @Override
    public Todo findTodoById(Long todoId) throws TodoCollecttionException {
        Optional<Todo> todoDTOOptional = todoRepository.findById(todoId);
        if (todoDTOOptional.isEmpty()) {
            throw new TodoCollecttionException(TodoCollecttionException.notFoundExeception(todoId));

        } else {
            return todoDTOOptional.get();
        }
    }

    @Override
    public Page<Todo> findAllTodo(FindAllTodoRequest findAllTodoRequest) {
        Pageable pageable = PageRequest.of(findAllTodoRequest.getPageNumber()-1, findAllTodoRequest.getNumberOfPerPages());
        Page<Todo> todoDTOS = todoRepository.findAll(pageable);
        return todoDTOS;
    }

    @Override
    public void deleteById(Long todoId) throws TodoCollecttionException {
            Optional<Todo> todoDTOOptional = todoRepository.findById(todoId);
            if (todoDTOOptional.isEmpty()) {
                throw new TodoCollecttionException(TodoCollecttionException.notFoundExeception(todoId));

            } else {
                todoRepository.deleteById(todoId);
            }


    }

    @Override
    public Todo updateTodo(UpdateTodoRequest updateTodoRequest, Long todoId) throws TodoCollecttionException {
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
            throw new TodoCollecttionException(TodoCollecttionException.notFoundExeception(todoId));
        }

    }

    @Override
    public Todo findByTodo(String todo) throws TodoCollecttionException {
   Todo foundTodo =     todoRepository.findTodoByTodo(todo);
   if(todo != null){
       return foundTodo;
   }
        throw new TodoCollecttionException(TodoCollecttionException.TodoNotFoundExeception(null));
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
