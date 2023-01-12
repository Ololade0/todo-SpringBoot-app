package semicolon.africa.todoapp.todoapp.dto.request;

import lombok.*;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@Builder

public class FindAllTodoRequest {
    private Long userId;
    private int numberOfPerPages;
    private int pageNumber;
}
