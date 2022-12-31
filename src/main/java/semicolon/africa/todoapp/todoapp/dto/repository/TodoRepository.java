package semicolon.africa.todoapp.todoapp.dto.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import semicolon.africa.todoapp.todoapp.dto.model.Todo;

@Repository
public interface TodoRepository extends JpaRepository<Todo, Long> {
    Todo findFirstByTodo(String todo);

}
