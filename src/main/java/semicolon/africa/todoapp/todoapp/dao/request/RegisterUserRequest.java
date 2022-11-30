package semicolon.africa.todoapp.todoapp.dao.request;

import lombok.*;
import semicolon.africa.todoapp.todoapp.dto.model.Role;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class RegisterUserRequest {
    private Long userId;
    private String firstName;
    private String lastName;
    private String email;
    private String phoneNumber;
    private String password;
}
