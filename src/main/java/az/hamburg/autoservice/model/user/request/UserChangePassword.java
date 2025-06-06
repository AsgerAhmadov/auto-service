package az.hamburg.autoservice.model.user.request;

import az.hamburg.autoservice.validation.user.UserPassword;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserChangePassword {

    private String oldPassword;

    @UserPassword
    private String newPassword;

    @UserPassword
    private String confirmPassword;
}
