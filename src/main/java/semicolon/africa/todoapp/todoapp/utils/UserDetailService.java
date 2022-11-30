package semicolon.africa.todoapp.todoapp.utils;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
;
import org.springframework.stereotype.Service;
import semicolon.africa.todoapp.todoapp.dto.model.User;
import semicolon.africa.todoapp.todoapp.service.UserService;


//@AllArgsConstructor
//@Service
//@Slf4j
//public class UserDetailService implements UserDetailsService {
//    private final UserService userService;
//
//    @Override
//    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
//        User user = null;
//        user = userService.findByEmail(username);
//        log.info("user-->{}",user);
//        return (UserDetails) new SecureUser(user);
//
//
//    }
//}
//
