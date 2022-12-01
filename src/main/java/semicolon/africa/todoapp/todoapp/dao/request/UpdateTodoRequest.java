package semicolon.africa.todoapp.todoapp.dao.request;

import lombok.*;

import java.util.Date;

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
