package semicolon.africa.todoapp.todoapp.Security.Config;
import lombok.AllArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import semicolon.africa.todoapp.todoapp.Security.filter.UserAuthenticationFilter;
import semicolon.africa.todoapp.todoapp.Security.filter.UserAuthorizationFilter;
import semicolon.africa.todoapp.todoapp.Security.jwt.JwtUtils;
import semicolon.africa.todoapp.todoapp.Security.manager.UserAuthenticationManager;
@Configuration
@AllArgsConstructor
public class SecurityConfig {
    private final JwtUtils jwtUtils;
    private final UserAuthenticationManager userAuthenticationManager;



    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        UsernamePasswordAuthenticationFilter filter =
                new UserAuthenticationFilter(userAuthenticationManager, jwtUtils);
        filter.setFilterProcessesUrl("/api/v1/user/login");

        return http.cors().and().csrf().disable()
                .sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS).and()
                .authorizeRequests()
                .antMatchers("/api/v1/user/register", "/api/v1/user/login").permitAll()
                .and()
                .authorizeRequests()
                .antMatchers(HttpMethod.GET, "/api/v1/user/all").hasAnyAuthority("BUY")
                .and()
                .addFilter(filter)
                .addFilterBefore(new UserAuthorizationFilter(), UserAuthenticationFilter.class)
                .build();
    }


}
