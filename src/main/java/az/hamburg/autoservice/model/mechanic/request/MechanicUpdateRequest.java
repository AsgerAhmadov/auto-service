package az.hamburg.autoservice.model.mechanic.request;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class MechanicUpdateRequest {

    private String name;
    private String email;
    private String specialty;
}
