package az.hamburg.autoservice.service;

import az.hamburg.autoservice.domain.RequestStatus;
import az.hamburg.autoservice.model.appointment.request.AppointmentCreateRequest;
import az.hamburg.autoservice.model.appointment.request.AppointmentUpdateRequest;
import az.hamburg.autoservice.model.appointment.response.AppointmentCreateResponse;
import az.hamburg.autoservice.model.appointment.response.AppointmentReadResponse;
import az.hamburg.autoservice.model.appointment.response.AppointmentStatusUpdateResponse;
import az.hamburg.autoservice.model.appointment.response.AppointmentUpdateResponse;

import java.util.List;

public interface AppointmentService {

    AppointmentCreateResponse create(Long vehicleId ,AppointmentCreateRequest createRequest);

    AppointmentUpdateResponse update(Long id, AppointmentUpdateRequest updateRequest);

    AppointmentReadResponse getById(Long id);

    List<AppointmentReadResponse> getAll();

    void  delete(Long userId,Long id);

     AppointmentStatusUpdateResponse statusUpdate( Long userId,Long appointmentId, RequestStatus status);

    List<AppointmentReadResponse> getAllStatusPending();

    void deleteCompleted(Long appointmentId);

}
