package az.hamburg.autoservice.model.appointment.request;

import az.hamburg.autoservice.validation.appointment.AppointmentServiceDescription;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class AppointmentCreateRequest {

    private String date;

    @AppointmentServiceDescription
    private String serviceDescription;

}
