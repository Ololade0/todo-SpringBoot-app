package semicolon.africa.todoapp.todoapp.Security.filter;


import semicolon.africa.todoapp.todoapp.dto.model.User;
;import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

//@AllArgsConstructor
//@Slf4j
//public class UserAuthenticationFilter extends UsernamePasswordAuthenticationFilter {
//    private final UserAuthenticationManager customAuthenticationManger;
//    private final JwtUtils jwtUtils;
//
//    @Override
//    public Authentication attemptAuthentication
//            (HttpServletRequest request, HttpServletResponse response)
//            throws AuthenticationException {
//        ObjectMapper objectMapper = new ObjectMapper();
//        User user;
//        try {
//            user = objectMapper.readValue(request.getInputStream(), User.class);
//        }catch (IOException exception){
//            throw new AuthenticationCredentialsNotFoundException(exception.getMessage());
//        }
//        //2. use authmanager to authenticate the user whose
//        // auth credentials are now contained in the auth obj
//        UsernamePasswordAuthenticationToken authenticationToken=
//                new UsernamePasswordAuthenticationToken(user.getEmail(), user.getPassword());
//        //3. get back the auth obj
//        Authentication authenticatedToken =customAuthenticationManger.authenticate(authenticationToken);
//        //4. store auth in security context
//        if (authenticatedToken!=null) {
//            SecurityContext securityContext = SecurityContextHolder.getContext();
//            securityContext.setAuthentication(authenticationToken);
//            return authenticatedToken;
//        }
//        throw new BadCredentialsException("Oh Oh!!");
//    }
//
//    @Override
//    protected void successfulAuthentication(HttpServletRequest request, HttpServletResponse response, FilterChain chain, Authentication authResult) throws IOException, ServletException {
//
//    }
//
//
//}
