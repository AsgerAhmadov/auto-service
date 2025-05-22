package az.hamburg.autoservice.model.user.request;

import az.hamburg.autoservice.validation.user.UserName;
import az.hamburg.autoservice.validation.user.UserPassword;
import az.hamburg.autoservice.validation.user.UserPhone;
import az.hamburg.autoservice.validation.user.UserUsername;
import jakarta.validation.constraints.Pattern;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class UserCreateRequest {

    @UserUsername
    private String username;

    @UserPassword
    private String password;

    @UserName
    private String name;

    @UserPhone
    private String phone;

    @Pattern(
            regexp = "^[A-Za-z0-9._%+-]+@[A-Za-z0-9.-]+\\.[A-Za-z]{2,6}$",
            message = "Email formatı düzgün deyil"
    )
    private String email;

}
