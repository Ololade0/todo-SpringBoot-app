package semicolon.africa.todoapp.todoapp.dto.response;

import lombok.*;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@ToString
public class UpdateTodoResponse {
    private Long id;
    private String message;
    private String todo;
}
