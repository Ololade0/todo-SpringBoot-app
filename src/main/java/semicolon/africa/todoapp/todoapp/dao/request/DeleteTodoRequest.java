package semicolon.africa.todoapp.todoapp.dao.request;

import lombok.*;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class DeleteTodoRequest {
    private Long userId;
    private Long todoId;
}
