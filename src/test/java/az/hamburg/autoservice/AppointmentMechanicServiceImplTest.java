package az.hamburg.autoservice;

import az.hamburg.autoservice.domain.Appointment;
import az.hamburg.autoservice.domain.AppointmentMechanic;
import az.hamburg.autoservice.domain.Mechanic;
import az.hamburg.autoservice.exception.handler.AppointmentMechanicNotFoundException;
import az.hamburg.autoservice.exception.handler.AppointmentNotFoundException;
import az.hamburg.autoservice.exception.handler.MechanicNotFoundException;
import az.hamburg.autoservice.mappers.AppointmentMechanicMapper;
import az.hamburg.autoservice.model.appointmentmechanic.request.AppointmentMechanicCreateRequest;
import az.hamburg.autoservice.model.appointmentmechanic.request.AppointmentMechanicUpdateRequest;
import az.hamburg.autoservice.model.appointmentmechanic.response.AppointmentMechanicCreateResponse;
import az.hamburg.autoservice.model.appointmentmechanic.response.AppointmentMechanicReadResponse;
import az.hamburg.autoservice.model.appointmentmechanic.response.AppointmentMechanicUpdateResponse;
import az.hamburg.autoservice.repository.AppointmentMechanicRepository;
import az.hamburg.autoservice.repository.AppointmentRepository;
import az.hamburg.autoservice.repository.MechanicRepository;
import az.hamburg.autoservice.service.impl.AppointmentMechanicServiceImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

public class AppointmentMechanicServiceImplTest {

    @InjectMocks
    private AppointmentMechanicServiceImpl service;

    @Mock
    private AppointmentMechanicRepository appointmentMechanicRepository;

    @Mock
    private AppointmentMechanicMapper appointmentMechanicMapper;

    @Mock
    private AppointmentRepository appointmentRepository;

    @Mock
    private MechanicRepository mechanicRepository;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testCreate_Success() {
        AppointmentMechanicCreateRequest request = new AppointmentMechanicCreateRequest();
        request.setAppointmentId(1L);
        request.setMechanicId(2L);

        Appointment appointment = new Appointment();
        appointment.setId(1L);
        Mechanic mechanic = new Mechanic();
        mechanic.setId(2L);

        AppointmentMechanic entity = new AppointmentMechanic();
        AppointmentMechanicCreateResponse response = new AppointmentMechanicCreateResponse();

        when(appointmentRepository.findById(1L)).thenReturn(Optional.of(appointment));
        when(mechanicRepository.findById(2L)).thenReturn(Optional.of(mechanic));
        when(appointmentMechanicMapper.createRequestToEntity(request)).thenReturn(entity);
        when(appointmentMechanicMapper.entityToCreateResponse(entity)).thenReturn(response);

        AppointmentMechanicCreateResponse result = service.create(request);

        assertNotNull(result);
        verify(appointmentMechanicRepository).save(entity);
    }

    @Test
    void testCreate_AppointmentNotFound() {
        AppointmentMechanicCreateRequest request = new AppointmentMechanicCreateRequest();
        request.setAppointmentId(1L);
        request.setMechanicId(2L);

        when(appointmentRepository.findById(1L)).thenReturn(Optional.empty());

        assertThrows(AppointmentNotFoundException.class, () -> service.create(request));
    }

    @Test
    void testCreate_MechanicNotFound() {
        AppointmentMechanicCreateRequest request = new AppointmentMechanicCreateRequest();
        request.setAppointmentId(1L);
        request.setMechanicId(2L);

        Appointment appointment = new Appointment();
        appointment.setId(1L);

        when(appointmentRepository.findById(1L)).thenReturn(Optional.of(appointment));
        when(mechanicRepository.findById(2L)).thenReturn(Optional.empty());

        assertThrows(MechanicNotFoundException.class, () -> service.create(request));
    }

    @Test
    void testUpdate_Success() {
        Long id = 1L;
        AppointmentMechanicUpdateRequest updateRequest = new AppointmentMechanicUpdateRequest();

        AppointmentMechanic entity = new AppointmentMechanic();
        AppointmentMechanic updatedEntity = new AppointmentMechanic();
        AppointmentMechanicUpdateResponse response = new AppointmentMechanicUpdateResponse();

        when(appointmentMechanicRepository.findById(id)).thenReturn(Optional.of(entity));
        when(appointmentMechanicMapper.updateRequestToEntity(entity, updateRequest)).thenReturn(updatedEntity);
        when(appointmentMechanicMapper.entityToUpdateResponse(updatedEntity)).thenReturn(response);

        AppointmentMechanicUpdateResponse result = service.update(id, updateRequest);

        assertNotNull(result);
        verify(appointmentMechanicRepository).save(updatedEntity);
    }

    @Test
    void testUpdate_NotFound() {
        Long id = 1L;
        AppointmentMechanicUpdateRequest updateRequest = new AppointmentMechanicUpdateRequest();

        when(appointmentMechanicRepository.findById(id)).thenReturn(Optional.empty());

        assertThrows(AppointmentMechanicNotFoundException.class, () -> service.update(id, updateRequest));
    }
    @Test
    void testGetById_Success() {
        Long id = 1L;
        AppointmentMechanic entity = new AppointmentMechanic();
        AppointmentMechanicReadResponse response = new AppointmentMechanicReadResponse();

        when(appointmentMechanicRepository.findById(id)).thenReturn(Optional.of(entity));
        when(appointmentMechanicMapper.entityToReadResponse(entity)).thenReturn(response);

        AppointmentMechanicReadResponse result = service.getById(id);

        assertNotNull(result);
    }

    @Test
    void testGetById_NotFound() {
        Long id = 1L;

        when(appointmentMechanicRepository.findById(id)).thenReturn(Optional.empty());

        assertThrows(AppointmentMechanicNotFoundException.class, () -> service.getById(id));
    }

    @Test
    void testGetAll_Success() {
        List<AppointmentMechanic> entityList = List.of(new AppointmentMechanic());
        AppointmentMechanicReadResponse response = new AppointmentMechanicReadResponse();

        when(appointmentMechanicRepository.findAll()).thenReturn(entityList);
        when(appointmentMechanicMapper.entityToReadResponse(any())).thenReturn(response);

        List<AppointmentMechanicReadResponse> result = service.getAll();

        assertEquals(1, result.size());
    }

    @Test
    void testDelete_Success() {
        Long id = 1L;
        AppointmentMechanic entity = new AppointmentMechanic();
        entity.setId(id);

        when(appointmentMechanicRepository.findById(id)).thenReturn(Optional.of(entity));

        service.delete(id);

        verify(appointmentMechanicRepository).deleteById(id);
    }

    @Test
    void testDelete_NotFound() {
        Long id = 1L;

        when(appointmentMechanicRepository.findById(id)).thenReturn(Optional.empty());

        assertThrows(AppointmentMechanicNotFoundException.class, () -> service.delete(id));
    }
}

