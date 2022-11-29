package semicolon.africa.todoapp.todoapp.dto.model;

import lombok.*;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.Date;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@ToString
@Builder
@Entity(name = "todo")
public class Todo {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long todoId;
    private String todo;
    private String description;
    private Date createdAt;
    private Boolean isCompleted;
    @ManyToOne()
    @JoinColumn(name = "user_id")
    private User user;

}
