package semicolon.africa.todoapp.todoapp.dao.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import org.springframework.stereotype.Repository;
import semicolon.africa.todoapp.todoapp.dao.model.Todo;


@Repository
public interface TodoRepository extends JpaRepository<Todo, Long> {
    Todo findFirstByTodo(String todo);


}
