package az.hamburg.autoservice.model.user.response;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserStatusUpdateResponse {
    private Long id;
    private boolean status;
    private String modifiedBy;
}
