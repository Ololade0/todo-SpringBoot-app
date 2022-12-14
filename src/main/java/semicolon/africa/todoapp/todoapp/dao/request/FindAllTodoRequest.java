package semicolon.africa.todoapp.todoapp.dao.request;

import lombok.*;

import java.nio.file.LinkOption;

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
