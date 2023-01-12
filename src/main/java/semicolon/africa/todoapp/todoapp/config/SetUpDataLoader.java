package semicolon.africa.todoapp.todoapp.config;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;
import semicolon.africa.todoapp.todoapp.dao.repository.UserRepository;

@Component
@Slf4j
public class SetUpDataLoader implements ApplicationListener<ContextRefreshedEvent> {
    @Autowired
    private UserRepository userRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;



    @Override
    public void onApplicationEvent(ContextRefreshedEvent event) {
        if (userRepository.findUserByEmail("adesuuser@gmail.com").isEmpty()){
//            User user = new User("Adesuyi", "Ololade","adesuuser@gmail.com", passwordEncoder.encode("password1234#"), "1234", RoleType.ADMIN);
//            userRepository.save(user);
        }
    }

}
