package az.hamburg.autoservice.model.appointmentmechanic.request;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
@Data
@NoArgsConstructor
@AllArgsConstructor
public class AppointmentMechanicCreateRequest {

    private Long appointmentId;
    private Long mechanicId;
    private String assignedAt;
}
