package az.hamburg.autoservice;

import az.hamburg.autoservice.domain.*;
import az.hamburg.autoservice.exception.handler.*;
import az.hamburg.autoservice.mappers.AppointmentMapper;
import az.hamburg.autoservice.model.appointment.request.AppointmentCreateRequest;
import az.hamburg.autoservice.model.appointment.request.AppointmentUpdateRequest;
import az.hamburg.autoservice.model.appointment.response.AppointmentCreateResponse;
import az.hamburg.autoservice.model.appointment.response.AppointmentReadResponse;
import az.hamburg.autoservice.model.appointment.response.AppointmentStatusUpdateResponse;
import az.hamburg.autoservice.model.appointment.response.AppointmentUpdateResponse;
import az.hamburg.autoservice.repository.AppointmentRepository;
import az.hamburg.autoservice.repository.UserRepository;
import az.hamburg.autoservice.repository.VehicleRepository;
import az.hamburg.autoservice.service.impl.AppointmentServiceImpl;
import az.hamburg.autoservice.util.AppointmentUtil;
import az.hamburg.autoservice.util.UserUtil;
import az.hamburg.autoservice.util.VehicleUtil;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class AppointmentServiceImplTest {

    @InjectMocks
    private AppointmentServiceImpl appointmentService;

    @Mock
    private AppointmentRepository appointmentRepository;
    @Mock
    private AppointmentMapper appointmentMapper;
    @Mock
    private VehicleRepository vehicleRepository;
    @Mock
    private UserRepository userRepository;

    @Test
    void testCreateAppointment_success() {
        Long vehicleId = 10L;
        AppointmentCreateRequest request = AppointmentUtil.createRequest();
        Appointment appointment = AppointmentUtil.appointment();
        AppointmentCreateResponse expectedResponse = AppointmentUtil.createResponse();

        when(vehicleRepository.findById(vehicleId)).thenReturn(Optional.of(VehicleUtil.vehicle()));
        when(appointmentMapper.createRequestToEntity(request)).thenReturn(appointment);
        when(appointmentMapper.entityToCreateResponse(appointment)).thenReturn(expectedResponse);

        AppointmentCreateResponse result = appointmentService.create(vehicleId, request);

        assertEquals(expectedResponse, result);
        verify(appointmentRepository).save(appointment);
    }

    @Test
    void testUpdateAppointment_success() {
        Long id = 1L;
        Appointment existingAppointment = AppointmentUtil.appointment();
        AppointmentUpdateRequest updateRequest = AppointmentUtil.updateRequest();
        Appointment updatedAppointment = AppointmentUtil.updatedAppointment();
        AppointmentUpdateResponse response = AppointmentUtil.updateResponse();

        when(appointmentRepository.findById(id)).thenReturn(Optional.of(existingAppointment));
        when(appointmentMapper.updateRequestToEntity(existingAppointment, updateRequest)).thenReturn(updatedAppointment);
        when(appointmentMapper.entityToUpdateResponse(updatedAppointment)).thenReturn(response);

        AppointmentUpdateResponse result = appointmentService.update(id, updateRequest);

        assertEquals(response, result);
        verify(appointmentRepository).save(updatedAppointment);
    }

    @Test
    void testGetById_success() {
        Long id = 1L;
        Appointment appointment = AppointmentUtil.appointment();
        AppointmentReadResponse expectedResponse = AppointmentUtil.readResponse();

        when(appointmentRepository.findById(id)).thenReturn(Optional.of(appointment));
        when(appointmentMapper.entityToReadResponse(appointment)).thenReturn(expectedResponse);

        AppointmentReadResponse result = appointmentService.getById(id);

        assertEquals(expectedResponse, result);
    }

    @Test
    void testGetAll_success() {
        List<Appointment> appointments = List.of(AppointmentUtil.appointment());
        List<AppointmentReadResponse> expected = List.of(AppointmentUtil.readResponse());

        when(appointmentRepository.findAll()).thenReturn(appointments);
        when(appointmentMapper.entityToReadResponse(any())).thenReturn(expected.get(0));

        List<AppointmentReadResponse> result = appointmentService.getAll();

        assertEquals(expected.size(), result.size());
    }

    @Test
    void testDeleteByAdmin_success() {
        Long userId = 5L;
        Long appointmentId = 1L;
        User admin = UserUtil.user(RoleType.ADMIN);
        Appointment appointment = AppointmentUtil.appointment();

        when(userRepository.findById(userId)).thenReturn(Optional.of(admin));
        when(appointmentRepository.findById(appointmentId)).thenReturn(Optional.of(appointment));

        appointmentService.delete(userId, appointmentId);

        verify(appointmentRepository).deleteById(appointment.getId());
    }

    @Test
    void testDeleteCompleted_success() {
        Long appointmentId = 1L;
        Appointment appointment = AppointmentUtil.appointment();
        appointment.setStatus(RequestStatus.COMPLETED);

        when(appointmentRepository.findById(appointmentId)).thenReturn(Optional.of(appointment));

        appointmentService.deleteCompleted(appointmentId);

        verify(appointmentRepository).deleteById(appointmentId);
    }
}

