package semicolon.africa.todoapp.todoapp.dao.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import semicolon.africa.todoapp.todoapp.dao.model.Todo;
import semicolon.africa.todoapp.todoapp.dao.model.User;

import java.util.List;
import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {

    User findUserByUserId( Long userId);
    User findByEmail(String email);
    Optional<User> findUserByEmail(String email);

    @Query("SELECT user FROM User user JOIN FETCH user.todos todos WHERE user.firstName = :firstName")
    User findByFirstName(@Param("firstName")String firstName);



}

