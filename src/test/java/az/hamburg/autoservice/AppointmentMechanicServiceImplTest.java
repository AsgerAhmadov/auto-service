package az.hamburg.autoservice;

import az.hamburg.autoservice.domain.AppointmentMechanic;
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
import az.hamburg.autoservice.util.AppointmentMechanicUtil;
import az.hamburg.autoservice.util.AppointmentUtil;
import az.hamburg.autoservice.util.MechanicUtil;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;
@ExtendWith(MockitoExtension.class)
class AppointmentMechanicServiceImplTest {

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

    @Test
    void testCreate_Success() {
        AppointmentMechanicCreateRequest request = AppointmentMechanicUtil.createRequest();
        AppointmentUtil.appointment().setId(request.getAppointmentId());

        MechanicUtil.mechanic().setId(request.getMechanicId());

        AppointmentMechanic entity = AppointmentMechanicUtil.appointmentMechanic();
        AppointmentMechanicCreateResponse response = AppointmentMechanicUtil.createResponse();

        when(appointmentRepository.findById(request.getAppointmentId())).thenReturn(Optional.of(AppointmentUtil.appointment()));
        when(mechanicRepository.findById(request.getMechanicId())).thenReturn(Optional.of(MechanicUtil.mechanic()));
        when(appointmentMechanicMapper.createRequestToEntity(request)).thenReturn(entity);
        when(appointmentMechanicRepository.save(any())).thenReturn(entity);
        when(appointmentMechanicMapper.entityToCreateResponse(entity)).thenReturn(response);

        AppointmentMechanicCreateResponse result = service.create(request);

        assertNotNull(result);
        verify(appointmentRepository).findById(request.getAppointmentId());
        verify(mechanicRepository).findById(request.getMechanicId());
    }

    @Test
    void testCreate_AppointmentNotFound() {
        AppointmentMechanicCreateRequest request = AppointmentMechanicUtil.createRequest();

        when(appointmentRepository.findById(request.getAppointmentId())).thenReturn(Optional.empty());

        assertThrows(AppointmentNotFoundException.class, () -> service.create(request));
    }

    @Test
    void testCreate_MechanicNotFound() {
        AppointmentMechanicCreateRequest request = AppointmentMechanicUtil.createRequest();
        AppointmentUtil.appointment().setId(request.getAppointmentId());

        when(appointmentRepository.findById(request.getAppointmentId())).thenReturn(Optional.of(AppointmentUtil.appointment()));
        when(mechanicRepository.findById(request.getMechanicId())).thenReturn(Optional.empty());

        assertThrows(MechanicNotFoundException.class, () -> service.create(request));
    }

    @Test
    void testUpdate_Success() {
        Long id = 1L;
        AppointmentMechanicUpdateRequest updateRequest = AppointmentMechanicUtil.updateRequest();
        AppointmentMechanic existing = AppointmentMechanicUtil.appointmentMechanic();
        AppointmentMechanic updated = AppointmentMechanicUtil.appointmentMechanic();
        AppointmentMechanicUpdateResponse response = AppointmentMechanicUtil.updateResponse();

        when(appointmentMechanicRepository.findById(id)).thenReturn(Optional.of(existing));
        when(appointmentMechanicMapper.updateRequestToEntity(existing, updateRequest)).thenReturn(updated);
        when(appointmentMechanicRepository.save(updated)).thenReturn(updated);
        when(appointmentMechanicMapper.entityToUpdateResponse(updated)).thenReturn(response);

        AppointmentMechanicUpdateResponse result = service.update(id, updateRequest);

        assertNotNull(result);
    }

    @Test
    void testUpdate_NotFound() {
        Long id = 1L;
        AppointmentMechanicUpdateRequest updateRequest = AppointmentMechanicUtil.updateRequest();

        when(appointmentMechanicRepository.findById(id)).thenReturn(Optional.empty());

        assertThrows(AppointmentMechanicNotFoundException.class, () -> service.update(id, updateRequest));
    }

    @Test
    void testGetById_Success() {
        Long id = 1L;
        AppointmentMechanic entity = AppointmentMechanicUtil.appointmentMechanic();
        AppointmentMechanicReadResponse response = AppointmentMechanicUtil.readResponse();

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
        List<AppointmentMechanic> list = List.of(AppointmentMechanicUtil.appointmentMechanic());
        AppointmentMechanicReadResponse response = AppointmentMechanicUtil.readResponse();

        when(appointmentMechanicRepository.findAll()).thenReturn(list);
        when(appointmentMechanicMapper.entityToReadResponse(any())).thenReturn(response);

        List<AppointmentMechanicReadResponse> result = service.getAll();

        assertEquals(1, result.size());
    }

    @Test
    void testDelete_Success() {
        Long id = 1L;
        AppointmentMechanic entity = AppointmentMechanicUtil.appointmentMechanic();
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

