package semicolon.africa.todoapp.todoapp.config;

import com.auth0.jwt.algorithms.Algorithm;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import semicolon.africa.todoapp.todoapp.Security.jwt.JwtUtils;


//
@Configuration
public class AppConfig {



    @Value("${jwt.secret}")
    private String jwtSecret;

    @Value("${jwt.issuer}")
    private String jwtIssuer;


    @Bean
    public JwtUtils jwtUtils() {
        return new JwtUtils(jwtIssuer, Algorithm.HMAC512(jwtSecret));
    }
//

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();

    }




}
