package semicolon.africa.todoapp.todoapp.Security.providers;

import lombok.AllArgsConstructor;

import org.springframework.stereotype.Component;


//@Component
//@AllArgsConstructor
//public class UserAuthenticationProvider implements AuthenticationProvider {
//    private final UserDetailService userDetailsServices;
//    private final PasswordEncoder passwordEncoder;
//
//    @Override
//    public Authentication authenticate(Authentication authentication) throws AuthenticationException {
//        UserDetails userDetails = userDetailsServices.loadUserByUsername((String) authentication.getPrincipal());
//        System.out.println("details--> "+userDetails);
//        if (userDetails != null) {
//            System.out.println("here--> "+userDetails);
//            if (isPasswordMatch(authentication, userDetails)) {
//                UsernamePasswordAuthenticationToken authenticationToken =
//                        new UsernamePasswordAuthenticationToken(userDetails,
//                                authentication.getCredentials(),
//                                userDetails.getAuthorities());
//                return authenticationToken;
//            }
//            throw new BadCredentialsException("Incorrect password");
//        }
//        throw new AuthenticationCredentialsNotFoundException("Email does not exist");
//    }
//
//    private boolean isPasswordMatch(Authentication authentication, UserDetails userDetails) {
//        return passwordEncoder.matches((String) authentication.getCredentials(), userDetails.getPassword());
//    }
//
//    @Override
//    public boolean supports(Class<?> authentication) {
//        var appAuthType =
//                UsernamePasswordAuthenticationToken.class;
//        return authentication.equals(appAuthType);
//    }

//}
