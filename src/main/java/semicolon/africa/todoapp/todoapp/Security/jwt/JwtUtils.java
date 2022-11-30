package semicolon.africa.todoapp.todoapp.Security.jwt;



import java.time.Instant;
import java.util.Date;
//
//@AllArgsConstructor
//public class JwtUtils {
//    private String issuer;
//    private Algorithm algorithm;
//
//
//    public String generateAccessToken(UserDetails user){
//        return JWT.create()
//                .withSubject(user.getUsername())
//                .withClaim("roles", user.getAuthorities().stream()
//                        .map(GrantedAuthority::getAuthority).toList())
//                .withIssuedAt(Instant.now())
//                .withExpiresAt(Date.from(Instant.now().plusSeconds(3600)))
//                .withIssuer(issuer)
//                .sign(algorithm);
//    }
//
//    public String generateRefreshToken(UserDetails user){
//        return JWT.create()
//                .withIssuer(issuer)
//                .withIssuedAt(Instant.now())
//                .withExpiresAt(Instant.now().plusSeconds(86400))
//                .withSubject(user.getUsername())
//                .sign(algorithm);
//    }
//}
