package semicolon.africa.todoapp.todoapp.dto.response;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
@Builder
public class DeleteTodoResponse {
    private String message;
}
