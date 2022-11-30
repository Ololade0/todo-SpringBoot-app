package semicolon.africa.todoapp.todoapp.Security.filter;


import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

import static org.springframework.http.HttpHeaders.AUTHORIZATION;
import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;
//@Slf4j
//public class UserAuthorizationFilter extends OncePerRequestFilter {
//    private ObjectMapper mapper = new ObjectMapper();
//    @Override
//    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
//        String authHeader = request.getHeader(AUTHORIZATION);
//        if (request.getServletPath().equals("/api/v1/user/login") || request.getServletPath().equals("/api/v1/user/register")) {
//            filterChain.doFilter(request, response);
//        } else {
//            if (authHeader != null && authHeader.startsWith("Bearer ")) {
//                try {
//                    String token = authHeader.substring("Bearer ".length());
//                    log.info("authorization_token->{}", token);
//                    JWTVerifier verifier = JWT.require(Algorithm.HMAC512("this-is-my-application-secret"))
//                            .build();
//                    DecodedJWT decodedJWT = verifier.verify(token);
//                    String subject = decodedJWT.getSubject();
//                    List<String> roles = decodedJWT.getClaim("roles").asList(String.class);
//
//                    UsernamePasswordAuthenticationToken authToken = new UsernamePasswordAuthenticationToken(subject, null,
//                            roles
//                                    .stream()
//                                    .map(SimpleGrantedAuthority::new)
//                                    .toList());
//                    SecurityContextHolder.getContext().setAuthentication(authToken);
//                    filterChain.doFilter(request, response);
//                } catch (Exception exception) {
//                    response.setContentType(APPLICATION_JSON_VALUE);
//                    mapper.writeValue(response.getOutputStream(), exception.getMessage());
//                }
//
//            } else filterChain.doFilter(request, response);
//        }
//
//
//    }
//}

