package semicolon.africa.todoapp.todoapp.dao.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import semicolon.africa.todoapp.todoapp.dao.model.User;

import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {
    User findUserByUserId(Long userId);
    User findByEmail(String email);
   Optional <User> findUserByEmail(String email);


}
