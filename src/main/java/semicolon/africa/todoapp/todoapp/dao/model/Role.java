package semicolon.africa.todoapp.todoapp.dao.model;


import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Setter
@Getter
@NoArgsConstructor

@Entity(name = "role")
public class Role {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
   private Long id;

    private RoleType roleType;
    public Role(RoleType roleType){
        this.roleType = roleType;
    }
//@ManyToOne()
//@JoinColumn(name = "user_id")
//    private User user;

}
