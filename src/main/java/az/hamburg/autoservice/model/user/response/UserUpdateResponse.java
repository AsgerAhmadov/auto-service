package az.hamburg.autoservice.model.user.response;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class UserUpdateResponse {

    private Long id;
    private String username;
    private String password;
    private String name;
    private String phone;
    private String email;
    private LocalDateTime updated;

}
