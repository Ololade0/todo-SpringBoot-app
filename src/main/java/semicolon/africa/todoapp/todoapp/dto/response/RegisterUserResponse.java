package semicolon.africa.todoapp.todoapp.dto.response;

import lombok.*;
import semicolon.africa.todoapp.todoapp.dao.model.Todo;

import java.util.List;

@Setter
@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class RegisterUserResponse {
    private Long userId;
    private String message;
    private String firstName;
    private String lastName;
    private List<Todo> todoList;
}
