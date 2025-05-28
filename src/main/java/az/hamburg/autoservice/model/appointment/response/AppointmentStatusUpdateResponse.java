package az.hamburg.autoservice.model.appointment.response;

import az.hamburg.autoservice.domain.RequestStatus;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class AppointmentStatusUpdateResponse {
    private Long id;
    private RequestStatus status;
    private LocalDateTime modified;
}
