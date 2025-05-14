package az.hamburg.autoservice.model.user.request;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class UserUpdateRequest {

    private String username;
    private String password;
    private String name;
    private String phone;
    private String email;

}
