package az.hamburg.autoservice.model.appointment.response;

import az.hamburg.autoservice.domain.RequestStatus;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class AppointmentCreateResponse {

    private Long id;
    private Long vehicleId;
    private String date;
    private String serviceDescription;
    private RequestStatus status;
    private LocalDateTime created;
}
