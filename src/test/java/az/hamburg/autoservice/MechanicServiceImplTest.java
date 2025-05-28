package az.hamburg.autoservice;

import az.hamburg.autoservice.domain.Mechanic;
import az.hamburg.autoservice.exception.handler.EmailAlreadyExistsException;
import az.hamburg.autoservice.exception.handler.MechanicNotFoundException;
import az.hamburg.autoservice.mappers.MechanicMapper;
import az.hamburg.autoservice.model.mechanic.request.MechanicCreateRequest;
import az.hamburg.autoservice.model.mechanic.request.MechanicUpdateRequest;
import az.hamburg.autoservice.model.mechanic.response.MechanicCreateResponse;
import az.hamburg.autoservice.model.mechanic.response.MechanicReadResponse;
import az.hamburg.autoservice.model.mechanic.response.MechanicUpdateResponse;
import az.hamburg.autoservice.repository.MechanicRepository;
import az.hamburg.autoservice.service.impl.MechanicServiceImpl;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Collections;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class MechanicServiceImplTest {

    @InjectMocks
    private MechanicServiceImpl mechanicService;

    @Mock
    private MechanicRepository mechanicRepository;

    @Mock
    private MechanicMapper mechanicMapper;

    @Test
    void testCreate_Success() {
        MechanicCreateRequest request = new MechanicCreateRequest();
        request.setEmail("test@example.com");

        when(mechanicRepository.findAll()).thenReturn(Collections.emptyList());

        Mechanic mechanic = new Mechanic();
        when(mechanicMapper.createRequestToEntity(request)).thenReturn(mechanic);
        when(mechanicRepository.save(any(Mechanic.class))).thenReturn(mechanic);

        MechanicCreateResponse expectedResponse = new MechanicCreateResponse();
        when(mechanicMapper.entityToCreateResponse(mechanic)).thenReturn(expectedResponse);

        MechanicCreateResponse response = mechanicService.create(request);

        assertNotNull(response);
        verify(mechanicRepository).save(any(Mechanic.class));
    }

    @Test
    void testCreate_EmailAlreadyExists() {
        MechanicCreateRequest request = new MechanicCreateRequest();
        request.setEmail("test@example.com");

        Mechanic existingMechanic = new Mechanic();
        existingMechanic.setEmail("test@example.com");

        when(mechanicRepository.findAll()).thenReturn(List.of(existingMechanic));

        assertThrows(EmailAlreadyExistsException.class,
                () -> mechanicService.create(request));
    }

    @Test
    void testUpdate_Success() {
        Long mechanicId = 1L;
        MechanicUpdateRequest updateRequest = new MechanicUpdateRequest();

        Mechanic existingMechanic = new Mechanic();
        when(mechanicRepository.findById(mechanicId)).thenReturn(Optional.of(existingMechanic));

        Mechanic updatedMechanic = new Mechanic();
        when(mechanicMapper.updateRequestToEntity(existingMechanic, updateRequest)).thenReturn(updatedMechanic);
        when(mechanicRepository.save(updatedMechanic)).thenReturn(updatedMechanic);

        MechanicUpdateResponse expectedResponse = new MechanicUpdateResponse();
        when(mechanicMapper.entityToUpdateResponse(updatedMechanic)).thenReturn(expectedResponse);

        MechanicUpdateResponse response = mechanicService.update(mechanicId, updateRequest);

        assertNotNull(response);
        verify(mechanicRepository).save(updatedMechanic);
    }

    @Test
    void testUpdate_MechanicNotFound() {
        Long mechanicId = 1L;
        MechanicUpdateRequest updateRequest = new MechanicUpdateRequest();

        when(mechanicRepository.findById(mechanicId)).thenReturn(Optional.empty());

        assertThrows(MechanicNotFoundException.class,
                () -> mechanicService.update(mechanicId, updateRequest));
    }

    @Test
    void testGetAll() {
        Mechanic mechanic1 = new Mechanic();
        Mechanic mechanic2 = new Mechanic();

        when(mechanicRepository.findAll()).thenReturn(List.of(mechanic1, mechanic2));
        when(mechanicMapper.entityToReadResponse(mechanic1)).thenReturn(new MechanicReadResponse());
        when(mechanicMapper.entityToReadResponse(mechanic2)).thenReturn(new MechanicReadResponse());

        List<MechanicReadResponse> responses = mechanicService.getAll();

        assertEquals(2, responses.size());
    }

    @Test
    void testGetId_Success() {
        Long mechanicId = 1L;
        Mechanic mechanic = new Mechanic();

        when(mechanicRepository.findById(mechanicId)).thenReturn(Optional.of(mechanic));

        MechanicReadResponse expectedResponse = new MechanicReadResponse();
        when(mechanicMapper.entityToReadResponse(mechanic)).thenReturn(expectedResponse);

        MechanicReadResponse response = mechanicService.getId(mechanicId);

        assertNotNull(response);
    }

    @Test
    void testGetId_MechanicNotFound() {
        Long mechanicId = 1L;

        when(mechanicRepository.findById(mechanicId)).thenReturn(Optional.empty());

        assertThrows(MechanicNotFoundException.class,
                () -> mechanicService.getId(mechanicId));
    }

    @Test
    void testDelete_Success() {
        Long mechanicId = 1L;
        Mechanic mechanic = new Mechanic();
        mechanic.setId(mechanicId);

        when(mechanicRepository.findById(mechanicId)).thenReturn(Optional.of(mechanic));

        mechanicService.delete(mechanicId);

        verify(mechanicRepository).delete(mechanic);
    }

    @Test
    void testDelete_MechanicNotFound() {
        Long mechanicId = 1L;

        when(mechanicRepository.findById(mechanicId)).thenReturn(Optional.empty());

        assertThrows(MechanicNotFoundException.class,
                () -> mechanicService.delete(mechanicId));
    }
}