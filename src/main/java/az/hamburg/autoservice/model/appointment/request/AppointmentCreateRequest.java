package az.hamburg.autoservice.model.appointment.request;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class AppointmentCreateRequest {

    private LocalDateTime date;
    private String serviceDescription;

}
