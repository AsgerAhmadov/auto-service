package az.hamburg.autoservice.util;

import az.hamburg.autoservice.domain.AppointmentMechanic;
import az.hamburg.autoservice.model.appointmentmechanic.request.AppointmentMechanicCreateRequest;
import az.hamburg.autoservice.model.appointmentmechanic.request.AppointmentMechanicUpdateRequest;
import az.hamburg.autoservice.model.appointmentmechanic.response.AppointmentMechanicCreateResponse;
import az.hamburg.autoservice.model.appointmentmechanic.response.AppointmentMechanicReadResponse;
import az.hamburg.autoservice.model.appointmentmechanic.response.AppointmentMechanicUpdateResponse;

public class AppointmentMechanicUtil {

    private AppointmentMechanicUtil() {

    }

    public static AppointmentMechanicCreateRequest createRequest() {
        AppointmentMechanicCreateRequest request = new AppointmentMechanicCreateRequest();
        request.setAppointmentId(1L);
        request.setMechanicId(2L);
        request.setAssignedAt("2025-06-01 10:00:00");
        return request;
    }

    public static AppointmentMechanic appointmentMechanic() {
        AppointmentMechanic entity = new AppointmentMechanic();
        entity.setId(100L); //
        entity.setAppointmentId(1L);
        entity.setMechanicId(2L);
        entity.setAssignedAt("2025-06-01 10:00:00");
        return entity;
    }

    public static AppointmentMechanicUpdateRequest updateRequest() {
        AppointmentMechanicUpdateRequest request = new AppointmentMechanicUpdateRequest();
        request.setAppointmentId(1L);
        request.setMechanicId(3L);
        request.setAssignedAt("2025-06-01 15:00:00");
        return request;
    }

    public static AppointmentMechanicCreateResponse createResponse() {
        AppointmentMechanicCreateResponse response = new AppointmentMechanicCreateResponse();
        response.setId(100L);
        response.setAppointmentId(1L);
        response.setMechanicId(2L);
        response.setAssignedAt("2025-06-01 10:00:00");
        return response;
    }

    public static AppointmentMechanicUpdateResponse updateResponse() {
        AppointmentMechanicUpdateResponse response = new AppointmentMechanicUpdateResponse();
        response.setId(100L);
        response.setAppointmentId(1L);
        response.setMechanicId(3L);
        response.setAssignedAt("2025-06-01 15:00:00");
        return response;
    }

    public static AppointmentMechanicReadResponse readResponse() {
        AppointmentMechanicReadResponse response = new AppointmentMechanicReadResponse();
        response.setId(100L);
        response.setAppointmentId(1L);
        response.setMechanicId(2L);
        response.setAssignedAt("2025-06-01 10:00:00");
        return response;
    }
}
