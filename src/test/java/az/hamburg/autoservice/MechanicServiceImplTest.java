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
import az.hamburg.autoservice.util.MechanicUtil;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Collections;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class MechanicServiceImplTest {

    @InjectMocks
    private MechanicServiceImpl mechanicService;

    @Mock
    private MechanicRepository mechanicRepository;

    @Mock
    private MechanicMapper mechanicMapper;

    @Test
    void testCreateMechanic_Success() {
        MechanicCreateRequest request = MechanicUtil.createRequest();
        Mechanic mechanic = MechanicUtil.mechanic();

        Mockito.when(mechanicRepository.findAll()).thenReturn(List.of());
        Mockito.when(mechanicMapper.createRequestToEntity(request)).thenReturn(mechanic);
        Mockito.when(mechanicMapper.entityToCreateResponse(mechanic)).thenReturn(MechanicUtil.mechanicCreateResponse());

        MechanicCreateResponse response = mechanicService.create(request);

        Assertions.assertNotNull(response);
        Mockito.verify(mechanicRepository).save(mechanic);
    }

    @Test
    void testCreateMechanic_EmailAlreadyExists() {
        MechanicCreateRequest request = MechanicUtil.createRequest();
        Mechanic existing = MechanicUtil.mechanic();

        Mockito.when(mechanicRepository.findAll()).thenReturn(List.of(existing));

        Assertions.assertThrows(EmailAlreadyExistsException.class, () -> {
            mechanicService.create(request);
        });
    }

    @Test
    void testUpdateMechanic_Success() {
        Mechanic mechanic = MechanicUtil.mechanic();
        MechanicUpdateRequest updateRequest = MechanicUtil.updateRequest();
        Mechanic updated = MechanicUtil.updatedMechanic();

        Mockito.when(mechanicRepository.findById(1L)).thenReturn(Optional.of(mechanic));
        Mockito.when(mechanicMapper.updateRequestToEntity(mechanic, updateRequest)).thenReturn(updated);
        Mockito.when(mechanicMapper.entityToUpdateResponse(updated)).thenReturn(MechanicUtil.mechanicUpdateResponse());

        MechanicUpdateResponse response = mechanicService.update(1L, updateRequest);

        Assertions.assertNotNull(response);
        Mockito.verify(mechanicRepository).save(updated);
    }

    @Test
    void testGetAllMechanics() {
        Mechanic mechanic = MechanicUtil.mechanic();
        Mockito.when(mechanicRepository.findAll()).thenReturn(List.of(mechanic));
        Mockito.when(mechanicMapper.entityToReadResponse(mechanic)).thenReturn(MechanicUtil.mechanicReadResponse());

        List<MechanicReadResponse> responses = mechanicService.getAll();

        Assertions.assertEquals(1, responses.size());
    }

    @Test
    void testGetMechanicById_Success() {
        Mechanic mechanic = MechanicUtil.mechanic();

        Mockito.when(mechanicRepository.findById(1L)).thenReturn(Optional.of(mechanic));
        Mockito.when(mechanicMapper.entityToReadResponse(mechanic)).thenReturn(MechanicUtil.mechanicReadResponse());

        MechanicReadResponse response = mechanicService.getId(1L);

        Assertions.assertNotNull(response);
    }

    @Test
    void testGetMechanicById_NotFound() {
        Mockito.when(mechanicRepository.findById(1L)).thenReturn(Optional.empty());

        Assertions.assertThrows(MechanicNotFoundException.class, () -> mechanicService.getId(1L));
    }

    @Test
    void testDeleteMechanic_Success() {
        Mechanic mechanic = MechanicUtil.mechanic();

        Mockito.when(mechanicRepository.findById(1L)).thenReturn(Optional.of(mechanic));

        mechanicService.delete(1L);

        Mockito.verify(mechanicRepository).delete(mechanic);
    }

    @Test
    void testDeleteMechanic_NotFound() {
        Mockito.when(mechanicRepository.findById(1L)).thenReturn(Optional.empty());

        Assertions.assertThrows(MechanicNotFoundException.class, () -> mechanicService.delete(1L));
    }
}