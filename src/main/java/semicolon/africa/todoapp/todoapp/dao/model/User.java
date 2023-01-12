package semicolon.africa.todoapp.todoapp.dao.model;


import lombok.*;
import org.springframework.validation.annotation.Validated;

import javax.persistence.*;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotNull;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;


@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
//@ToString
@Builder
@Entity(name = "user")
@Validated()

public class User  {
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
    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    private Set<Role> roles = new HashSet<>();

     public User(String firstName, String lastName, String email, String phoneNumber, String password, RoleType roleType) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.phoneNumber = phoneNumber;
        this.password = password;

        if (roles == null) {
            roles = new HashSet<>();
            roles.add(new Role(roleType));
        }
    }



}

