package az.hamburg.autoservice.model.mechanic.response;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class MechanicReadResponse {

    private Long id;
    private String name;
    private String email;
    private String specialty;
    private LocalDateTime created;
    private LocalDateTime modified;
}
