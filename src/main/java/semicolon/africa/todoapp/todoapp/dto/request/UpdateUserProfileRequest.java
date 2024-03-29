package semicolon.africa.todoapp.todoapp.dto.request;

import lombok.*;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class UpdateUserProfileRequest {
    private Long userId;
    private String firstName;
    private String lastName;
    private String email;
    private String phoneNumber;
}
