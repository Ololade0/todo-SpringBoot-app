package semicolon.africa.todoapp.todoapp.dao.request;

import lombok.*;

import java.time.LocalDateTime;
import java.util.Date;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class CreateTodoRequest {
    private Long todoId;
    private String todo;
    private String description;
    private Date createdAt;
    private boolean isCompleted;

}
