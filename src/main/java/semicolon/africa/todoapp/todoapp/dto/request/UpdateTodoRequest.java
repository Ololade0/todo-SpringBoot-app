package semicolon.africa.todoapp.todoapp.dto.request;

import lombok.*;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class UpdateTodoRequest {
    private Long userId;
//    private Long todoId;
    private String todo;
    private String description;
    private Boolean isCompleted;

}
