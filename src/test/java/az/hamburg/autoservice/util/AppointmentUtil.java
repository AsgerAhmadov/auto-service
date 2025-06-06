package az.hamburg.autoservice.util;

import az.hamburg.autoservice.domain.Appointment;
import az.hamburg.autoservice.domain.RequestStatus;
import az.hamburg.autoservice.model.appointment.request.AppointmentCreateRequest;
import az.hamburg.autoservice.model.appointment.request.AppointmentUpdateRequest;
import az.hamburg.autoservice.model.appointment.response.AppointmentCreateResponse;
import az.hamburg.autoservice.model.appointment.response.AppointmentReadResponse;
import az.hamburg.autoservice.model.appointment.response.AppointmentUpdateResponse;

import java.time.LocalDateTime;

public class AppointmentUtil {

    private AppointmentUtil() {
    }

    public static AppointmentCreateRequest createRequest() {
        AppointmentCreateRequest request = new AppointmentCreateRequest();
        request.setDate("2025-06-05");
        request.setServiceDescription("Yağ dəyişimi və ümumi texniki baxış");
        return request;
    }

    public static AppointmentUpdateRequest updateRequest() {
        AppointmentUpdateRequest request = new AppointmentUpdateRequest();
        request.setDate("2025-07-01");
        request.setServiceDescription("Fren sisteminin yoxlanması");
        return request;
    }

    public static Appointment appointment() {
        Appointment appointment = new Appointment();
        appointment.setId(1L);
        appointment.setDate("2025-06-05");
        appointment.setServiceDescription("Yağ dəyişimi və ümumi texniki baxış");
        appointment.setVehicleId(10L);
        appointment.setStatus(RequestStatus.PENDING);
        return appointment;
    }

    public static Appointment updatedAppointment() {
        Appointment appointment = appointment();
        appointment.setDate("2025-07-01");
        appointment.setServiceDescription("Fren sisteminin yoxlanması");
        return appointment;
    }

    public static AppointmentCreateResponse createResponse() {
        AppointmentCreateResponse response = new AppointmentCreateResponse();
        response.setId(1L);
        response.setDate("2025-06-10");
        response.setServiceDescription("Yağ dəyişmə və əyləc yoxlanışı");
        response.setStatus(RequestStatus.PENDING);
        return response;
    }

    public static AppointmentUpdateResponse updateResponse() {
        AppointmentUpdateResponse response = new AppointmentUpdateResponse();
        response.setId(1L);
        response.setVehicleId(101L);
        response.setDate("2025-06-15");
        response.setServiceDescription("Təkər dəyişimi və balanslaşdırma");
        response.setStatus(RequestStatus.COMPLETED);
        response.setModified(LocalDateTime.now());
        return response;
    }

    public static AppointmentReadResponse readResponse() {
        AppointmentReadResponse response = new AppointmentReadResponse();
        response.setId(1L);
        response.setVehicleId(101L);
        response.setDate("2025-06-10");
        response.setServiceDescription("Yağ dəyişimi və ümumi yoxlama");
        response.setStatus(RequestStatus.PENDING);
        response.setCreated(LocalDateTime.of(2025, 6, 1, 10, 0));
        response.setModified(LocalDateTime.of(2025, 6, 5, 12, 30));
        return response;
    }
}

