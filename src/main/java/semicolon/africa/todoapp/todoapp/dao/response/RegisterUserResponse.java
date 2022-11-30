package semicolon.africa.todoapp.todoapp.dao.response;

import lombok.*;
import org.springframework.beans.factory.annotation.Autowired;

@Setter
@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class RegisterUserResponse {
    private String message;
    private int code;
    private Long userId;
    private String phoneNumber;
}
