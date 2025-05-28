package az.hamburg.autoservice.service;



import az.hamburg.autoservice.model.appointmentmechanic.request.AppointmentMechanicCreateRequest;
import az.hamburg.autoservice.model.appointmentmechanic.request.AppointmentMechanicUpdateRequest;
import az.hamburg.autoservice.model.appointmentmechanic.response.AppointmentMechanicCreateResponse;
import az.hamburg.autoservice.model.appointmentmechanic.response.AppointmentMechanicReadResponse;
import az.hamburg.autoservice.model.appointmentmechanic.response.AppointmentMechanicUpdateResponse;

import java.util.List;

public interface AppointmentMechanicService {

    AppointmentMechanicCreateResponse create(AppointmentMechanicCreateRequest createRequest);

    AppointmentMechanicUpdateResponse update(Long id, AppointmentMechanicUpdateRequest updateRequest);

    AppointmentMechanicReadResponse getById(Long id);

    List<AppointmentMechanicReadResponse> getAll();

    void  delete(Long id);

}
