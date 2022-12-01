package semicolon.africa.todoapp.todoapp.Security.manager;


import lombok.AllArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.stereotype.Component;
import semicolon.africa.todoapp.todoapp.Security.providers.UserAuthenticationProvider;

@AllArgsConstructor
@Component
public class UserAuthenticationManager implements AuthenticationManager {

    private final UserAuthenticationProvider authenticationProvider;
    @Override
    public Authentication authenticate(Authentication authentication) throws AuthenticationException {
        return authenticationProvider.authenticate(authentication);
    }
}
