package semicolon.africa.todoapp.todoapp.dto.model;

import lombok.*;
import org.springframework.validation.annotation.Validated;

import javax.persistence.*;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotNull;
import java.util.ArrayList;
import java.util.List;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@ToString
@Builder
@Entity(name = "user")
@Validated()
public class
User {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long userId;
    @NotNull(message = "FirstName cannot be null")
    private String firstName;

    @NotNull(message = "FirstName cannot be null")
    private String lastName;
    @Email(message = "Email cannot be null")
    private String email;
    private String phoneNumber;
    private String password;
    private boolean isEnabled;
    @OneToMany(mappedBy = "user", fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    private List<Todo> todoList = new ArrayList<>();
    private Role role;

}
