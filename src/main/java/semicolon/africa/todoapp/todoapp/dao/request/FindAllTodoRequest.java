package semicolon.africa.todoapp.todoapp.dao.request;

import lombok.*;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@Builder

public class FindAllTodoRequest {
    private int numberOfPerPages;
    private int pageNumber;
}
