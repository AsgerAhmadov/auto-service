package az.hamburg.autoservice.model.appointmentmechanic.response;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class AppointmentMechanicUpdateResponse {

    private Long id;
    private Long appointmentId;
    private Long mechanicId;
    private String assignedAt;
    private LocalDateTime modified;
}
