package semicolon.africa.todoapp.todoapp.dto.request;

import lombok.*;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class CreateTodoRequest {
    private Long userId;
    private String todo;
    private String description;
//    private Date createdAt;
    private boolean isCompleted;

}
