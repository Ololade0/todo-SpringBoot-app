package semicolon.africa.todoapp.todoapp.dao.request;

import lombok.*;

import javax.mail.internet.InternetAddress;
import javax.validation.constraints.Email;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class MailRequest {
    @Email
    private String sender;
    @Email
    private String receiver;
    private String subject;
    private String body;
    private String email;

}
