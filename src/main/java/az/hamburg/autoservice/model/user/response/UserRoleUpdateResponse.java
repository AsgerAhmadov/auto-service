package az.hamburg.autoservice.model.user.response;

import az.hamburg.autoservice.domain.RoleType;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserRoleUpdateResponse {
    private Long id;
    private RoleType roleType;
    private LocalDateTime updated;
}
