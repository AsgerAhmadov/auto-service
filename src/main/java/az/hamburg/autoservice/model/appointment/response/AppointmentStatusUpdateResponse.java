package az.hamburg.autoservice.model.appointment.response;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class AppointmentStatusUpdateResponse {
    private Long id;
    private boolean status;
    private LocalDateTime modified;
}
