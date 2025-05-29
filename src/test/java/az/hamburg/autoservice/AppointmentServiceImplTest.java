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
    void testCreate_Success() {
        Long vehicleId = 1L;
        AppointmentCreateRequest request = new AppointmentCreateRequest();

        Vehicle vehicle = new Vehicle();
        vehicle.setId(vehicleId);
        when(vehicleRepository.findById(vehicleId)).thenReturn(Optional.of(vehicle));

        Appointment appointment = new Appointment();
        when(appointmentMapper.createRequestToEntity(request)).thenReturn(appointment);
        when(appointmentRepository.save(any(Appointment.class))).thenReturn(appointment);

        AppointmentCreateResponse expectedResponse = new AppointmentCreateResponse();
        when(appointmentMapper.entityToCreateResponse(appointment)).thenReturn(expectedResponse);

        AppointmentCreateResponse response = appointmentService.create(vehicleId, request);

        assertNotNull(response);
        verify(appointmentRepository).save(any(Appointment.class));
    }

    @Test
    void testCreate_VehicleNotFound() {
        Long vehicleId = 1L;
        AppointmentCreateRequest request = new AppointmentCreateRequest();

        when(vehicleRepository.findById(vehicleId)).thenReturn(Optional.empty());

        assertThrows(VehicleNotFoundException.class, () -> appointmentService.create(vehicleId, request));
    }

    @Test
    void testUpdate_Success() {
        Long appointmentId = 1L;
        AppointmentUpdateRequest updateRequest = new AppointmentUpdateRequest();

        Appointment existing = new Appointment();
        when(appointmentRepository.findById(appointmentId)).thenReturn(Optional.of(existing));

        Appointment updated = new Appointment();
        when(appointmentMapper.updateRequestToEntity(existing, updateRequest)).thenReturn(updated);
        when(appointmentRepository.save(updated)).thenReturn(updated);

        AppointmentUpdateResponse expectedResponse = new AppointmentUpdateResponse();
        when(appointmentMapper.entityToUpdateResponse(updated)).thenReturn(expectedResponse);

        AppointmentUpdateResponse response = appointmentService.update(appointmentId, updateRequest);

        assertNotNull(response);
        verify(appointmentRepository).save(updated);
    }

    @Test
    void testUpdate_AppointmentNotFound() {
        Long appointmentId = 1L;
        AppointmentUpdateRequest updateRequest = new AppointmentUpdateRequest();

        when(appointmentRepository.findById(appointmentId)).thenReturn(Optional.empty());

        assertThrows(AppointmentNotFoundException.class,
                () -> appointmentService.update(appointmentId, updateRequest));
    }

    @Test
    void testGetById_Success() {
        Long appointmentId = 1L;
        Appointment appointment = new Appointment();

        when(appointmentRepository.findById(appointmentId)).thenReturn(Optional.of(appointment));

        AppointmentReadResponse expectedResponse = new AppointmentReadResponse();
        when(appointmentMapper.entityToReadResponse(appointment)).thenReturn(expectedResponse);

        AppointmentReadResponse response = appointmentService.getById(appointmentId);

        assertNotNull(response);
    }

    @Test
    void testGetById_AppointmentNotFound() {
        Long appointmentId = 1L;

        when(appointmentRepository.findById(appointmentId)).thenReturn(Optional.empty());

        assertThrows(AppointmentNotFoundException.class,
                () -> appointmentService.getById(appointmentId));
    }

    @Test
    void testGetAll() {
        Appointment a1 = new Appointment();
        Appointment a2 = new Appointment();

        when(appointmentRepository.findAll()).thenReturn(List.of(a1, a2));
        when(appointmentMapper.entityToReadResponse(a1)).thenReturn(new AppointmentReadResponse());
        when(appointmentMapper.entityToReadResponse(a2)).thenReturn(new AppointmentReadResponse());

        List<AppointmentReadResponse> responses = appointmentService.getAll();

        assertEquals(2, responses.size());
    }

    @Test
    void testDelete_Success() {
        Long userId = 1L;
        Long appointmentId = 1L;

        User adminUser = new User();
        adminUser.setId(userId);
        adminUser.setRoleType(RoleType.ADMIN);

        Appointment appointment = new Appointment();
        appointment.setId(appointmentId);

        when(userRepository.findById(userId)).thenReturn(Optional.of(adminUser));
        when(appointmentRepository.findById(appointmentId)).thenReturn(Optional.of(appointment));

        appointmentService.delete(userId, appointmentId);

        verify(appointmentRepository, times(1)).deleteById(appointmentId);
    }

    @Test
    void testDelete_AppointmentNotFound() {
        Long userId = 1L;
        Long appointmentId = 1L;

        User adminUser = new User();
        adminUser.setId(userId);
        adminUser.setRoleType(RoleType.ADMIN);

        when(userRepository.findById(userId)).thenReturn(Optional.of(adminUser));
        when(appointmentRepository.findById(appointmentId)).thenReturn(Optional.empty());

        assertThrows(AppointmentNotFoundException.class,
                () -> appointmentService.delete(userId, appointmentId));
    }

    @Test
    void testDelete_UserNotFound() {
        Long userId = 1L;
        Long appointmentId = 1L;

        when(userRepository.findById(userId)).thenReturn(Optional.empty());

        assertThrows(UserNotFoundException.class,
                () -> appointmentService.delete(userId, appointmentId));
    }

    @Test
    void testDelete_UserUnauthorized() {
        Long userId = 1L;
        Long appointmentId = 1L;

        User normalUser = new User();
        normalUser.setId(userId);
        normalUser.setRoleType(RoleType.USER);  // ADMIN deyil

        when(userRepository.findById(userId)).thenReturn(Optional.of(normalUser));

        assertThrows(UserUnAuthorizedException.class,
                () -> appointmentService.delete(userId, appointmentId));
    }

    @Test
    void testGetAllStatusPending() {
        Appointment a1 = new Appointment();
        Appointment a2 = new Appointment();

        when(appointmentRepository.getAllAppointmentByStatus(RequestStatus.PENDING))
                .thenReturn(List.of(a1, a2));
        when(appointmentMapper.entityToReadResponse(a1)).thenReturn(new AppointmentReadResponse());
        when(appointmentMapper.entityToReadResponse(a2)).thenReturn(new AppointmentReadResponse());

        List<AppointmentReadResponse> responses = appointmentService.getAllStatusPending();

        assertEquals(2, responses.size());
    }


    @Test
    void testStatusUpdate_Success() {
        Long userId = 1L;
        Long appointmentId = 2L;
        RequestStatus newStatus = RequestStatus.ACCEPTED;

        User user = new User();
        user.setId(userId);
        user.setRoleType(RoleType.MODERATOR);

        Appointment appointment = new Appointment();
        appointment.setId(appointmentId);

        AppointmentStatusUpdateResponse expectedResponse = new AppointmentStatusUpdateResponse();

        when(userRepository.findById(userId)).thenReturn(Optional.of(user));
        when(appointmentRepository.findById(appointmentId)).thenReturn(Optional.of(appointment));
        when(appointmentRepository.save(any(Appointment.class))).thenReturn(appointment);
        when(appointmentMapper.entityToAppointmentStatusUpdateResponse(appointment)).thenReturn(expectedResponse);

        AppointmentStatusUpdateResponse response = appointmentService.statusUpdate(userId, appointmentId, newStatus);

        assertNotNull(response);
        assertEquals(expectedResponse, response);

        verify(appointmentRepository, times(1)).save(appointment);
        verify(appointmentMapper, times(1)).entityToAppointmentStatusUpdateResponse(appointment);
    }

    @Test
    void testStatusUpdate_UserNotFound() {
        Long userId = 1L;
        Long appointmentId = 2L;
        RequestStatus newStatus = RequestStatus.ACCEPTED;

        when(userRepository.findById(userId)).thenReturn(Optional.empty());

        assertThrows(UserNotFoundException.class,
                () -> appointmentService.statusUpdate(userId, appointmentId, newStatus));

        verify(appointmentRepository, never()).findById(any());
        verify(appointmentRepository, never()).save(any());
        verify(appointmentMapper, never()).entityToAppointmentStatusUpdateResponse(any());
    }

    @Test
    void testStatusUpdate_AppointmentNotFound() {
        Long userId = 1L;
        Long appointmentId = 2L;
        RequestStatus newStatus = RequestStatus.ACCEPTED;

        User user = new User();
        user.setId(userId);
        user.setRoleType(RoleType.MODERATOR);

        when(userRepository.findById(userId)).thenReturn(Optional.of(user));
        when(appointmentRepository.findById(appointmentId)).thenReturn(Optional.empty());

        assertThrows(AppointmentNotFoundException.class,
                () -> appointmentService.statusUpdate(userId, appointmentId, newStatus));

        verify(appointmentRepository, never()).save(any());
        verify(appointmentMapper, never()).entityToAppointmentStatusUpdateResponse(any());
    }

    @Test
    void testStatusUpdate_Unauthorized() {
        Long userId = 1L;
        Long appointmentId = 2L;
        RequestStatus newStatus = RequestStatus.ACCEPTED;

        User user = new User();
        user.setId(userId);
        user.setRoleType(RoleType.USER); // ADMIN və ya MODERATOR deyil

        Appointment appointment = new Appointment();
        appointment.setId(appointmentId);

        when(userRepository.findById(userId)).thenReturn(Optional.of(user));
        when(appointmentRepository.findById(appointmentId)).thenReturn(Optional.of(appointment));

        assertThrows(UserUnAuthorizedException.class,
                () -> appointmentService.statusUpdate(userId, appointmentId, newStatus));

        verify(appointmentRepository, never()).save(any());
        verify(appointmentMapper, never()).entityToAppointmentStatusUpdateResponse(any());
    }

    @Test
    void testDeleteCompleted_Success() {
        Long appointmentId = 1L;

        Appointment appointment = new Appointment();
        appointment.setId(appointmentId);
        appointment.setStatus(RequestStatus.COMPLETED);

        when(appointmentRepository.findById(appointmentId)).thenReturn(Optional.of(appointment));

        appointmentService.deleteCompleted(appointmentId);

        verify(appointmentRepository).deleteById(appointmentId);
    }

    @Test
    void testDeleteCompleted_NotCompleted() {
        Long appointmentId = 1L;

        Appointment appointment = new Appointment();
        appointment.setId(appointmentId);
        appointment.setStatus(RequestStatus.PENDING);

        when(appointmentRepository.findById(appointmentId)).thenReturn(Optional.of(appointment));

        assertThrows(AppointmentNotCompleted.class,
                () -> appointmentService.deleteCompleted(appointmentId));
    }

    @Test
    void testDeleteCompleted_AppointmentNotFound() {
        Long appointmentId = 1L;

        when(appointmentRepository.findById(appointmentId)).thenReturn(Optional.empty());

        assertThrows(AppointmentNotFoundException.class,
                () -> appointmentService.deleteCompleted(appointmentId));
    }
}
