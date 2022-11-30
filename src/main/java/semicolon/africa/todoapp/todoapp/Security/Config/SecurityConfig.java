package semicolon.africa.todoapp.todoapp.Security.Config;



//
//@AllArgsConstructor
//public class SecurityConfig {
//    private final JwtUtils jwtUtils;
//    private final UserAuthenticationManager userAuthenticationManager;
//
//
//
//    @Bean
//    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
//        UsernamePasswordAuthenticationFilter filter =
//                new UserAuthenticationFilter(userAuthenticationManager, jwtUtils);
//        filter.setFilterProcessesUrl("/api/v1/user/login");
//
//        return http.cors().and().csrf().disable()
//                .sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS).and()
//                .authorizeRequests()
//                .antMatchers("/api/v1/user/register", "/api/v1/user/login").permitAll()
//                .and()
//                .authorizeRequests()
//                .antMatchers(HttpMethod.GET, "/api/v1/user/all").hasAnyAuthority("BUY")
//                .and()
//                .addFilter(filter)
//                .addFilterBefore(new UserAuthorizationFilter(), UserAuthenticationFilter.class)
//                .build();
//    }
//
//
//}
