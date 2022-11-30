package semicolon.africa.todoapp.todoapp.dao.request;

import lombok.*;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@ToString
public class DeleteTodoIdRequest {
    private Long userId;
    private Long todoId;
}
