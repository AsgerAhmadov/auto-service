package az.hamburg.autoservice.model.user.response;

import az.hamburg.autoservice.domain.RoleType;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class UserReadResponse {

    private Long id;
    private String username;
    private String password;
    private String name;
    private String phone;
    private String email;
    private RoleType roleType;
    private LocalDateTime created;
    private LocalDateTime modified;

}
