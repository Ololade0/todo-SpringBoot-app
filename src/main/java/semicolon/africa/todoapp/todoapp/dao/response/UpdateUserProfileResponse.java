package semicolon.africa.todoapp.todoapp.dao.response;

import lombok.*;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class UpdateUserProfileResponse {
    private String message;
    private Long id;
}
