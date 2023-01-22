package semicolon.africa.todoapp.todoapp.dao.model;

import lombok.*;

import javax.persistence.*;
import java.util.Date;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@ToString
@Builder
@Entity(name = "Todo")
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



