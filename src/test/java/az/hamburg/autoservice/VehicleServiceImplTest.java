package az.hamburg.autoservice;

import az.hamburg.autoservice.domain.User;
import az.hamburg.autoservice.domain.Vehicle;
import az.hamburg.autoservice.exception.handler.PlateNumberAlreadyExistsException;
import az.hamburg.autoservice.exception.handler.VehicleNotFoundException;
import az.hamburg.autoservice.mappers.VehicleMapper;
import az.hamburg.autoservice.model.vehicle.request.VehicleCreateRequest;
import az.hamburg.autoservice.model.vehicle.request.VehicleUpdateRequest;
import az.hamburg.autoservice.model.vehicle.response.VehicleCreateResponse;
import az.hamburg.autoservice.model.vehicle.response.VehicleReadResponse;
import az.hamburg.autoservice.model.vehicle.response.VehicleUpdateResponse;
import az.hamburg.autoservice.repository.UserRepository;
import az.hamburg.autoservice.repository.VehicleRepository;
import az.hamburg.autoservice.service.impl.VehicleServiceImpl;
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
class VehicleServiceImplTest {

    @InjectMocks
    private VehicleServiceImpl vehicleService;

    @Mock
    private VehicleRepository vehicleRepository;

    @Mock
    private VehicleMapper vehicleMapper;

    @Mock
    private UserRepository userRepository;

    @Test
    void testCreate_Success() {
        Long userId = 1L;
        VehicleCreateRequest createRequest = new VehicleCreateRequest();
        createRequest.setPlateNumber("90-AB-123");

        User user = new User();
        user.setId(userId);

        when(userRepository.findById(userId)).thenReturn(Optional.of(user));
        when(vehicleRepository.findAll()).thenReturn(Collections.emptyList());

        Vehicle vehicle = new Vehicle();
        when(vehicleMapper.createRequestToEntity(createRequest)).thenReturn(vehicle);
        when(vehicleRepository.save(any(Vehicle.class))).thenReturn(vehicle);

        VehicleCreateResponse expectedResponse = new VehicleCreateResponse();
        when(vehicleMapper.entityToCreateResponse(vehicle)).thenReturn(expectedResponse);

        VehicleCreateResponse response = vehicleService.create(userId, createRequest);

        assertNotNull(response);
        verify(userRepository).findById(userId);
        verify(vehicleRepository).save(any(Vehicle.class));
    }

    @Test
    void testCreate_PlateNumberAlreadyExists() {
        Long userId = 1L;
        VehicleCreateRequest createRequest = new VehicleCreateRequest();
        createRequest.setPlateNumber("90-AB-123");

        User user = new User();
        user.setId(userId);

        Vehicle existingVehicle = new Vehicle();
        existingVehicle.setPlateNumber("90-AB-123");

        when(userRepository.findById(userId)).thenReturn(Optional.of(user));
        when(vehicleRepository.findAll()).thenReturn(List.of(existingVehicle));

        assertThrows(PlateNumberAlreadyExistsException.class,
                () -> vehicleService.create(userId, createRequest));
    }

    @Test
    void testUpdate_Success() {
        Long vehicleId = 1L;
        VehicleUpdateRequest updateRequest = new VehicleUpdateRequest();

        Vehicle existingVehicle = new Vehicle();
        when(vehicleRepository.findById(vehicleId)).thenReturn(Optional.of(existingVehicle));

        Vehicle updatedVehicle = new Vehicle();
        when(vehicleMapper.updateRequestToEntity(existingVehicle, updateRequest)).thenReturn(updatedVehicle);
        when(vehicleRepository.save(updatedVehicle)).thenReturn(updatedVehicle);

        VehicleUpdateResponse expectedResponse = new VehicleUpdateResponse();
        when(vehicleMapper.entityToUpdateResponse(updatedVehicle)).thenReturn(expectedResponse);

        VehicleUpdateResponse response = vehicleService.update(vehicleId, updateRequest);

        assertNotNull(response);
        verify(vehicleRepository).save(updatedVehicle);
    }

    @Test
    void testUpdate_VehicleNotFound() {
        Long vehicleId = 1L;
        VehicleUpdateRequest updateRequest = new VehicleUpdateRequest();

        when(vehicleRepository.findById(vehicleId)).thenReturn(Optional.empty());

        assertThrows(VehicleNotFoundException.class,
                () -> vehicleService.update(vehicleId, updateRequest));
    }

    @Test
    void testGetById_Success() {
        Long vehicleId = 1L;
        Vehicle vehicle = new Vehicle();

        when(vehicleRepository.findById(vehicleId)).thenReturn(Optional.of(vehicle));

        VehicleReadResponse expectedResponse = new VehicleReadResponse();
        when(vehicleMapper.entityToReadResponse(vehicle)).thenReturn(expectedResponse);

        VehicleReadResponse response = vehicleService.getById(vehicleId);

        assertNotNull(response);
    }

    @Test
    void testGetById_VehicleNotFound() {
        Long vehicleId = 1L;

        when(vehicleRepository.findById(vehicleId)).thenReturn(Optional.empty());

        assertThrows(VehicleNotFoundException.class,
                () -> vehicleService.getById(vehicleId));
    }

    @Test
    void testGetAll() {
        Vehicle vehicle1 = new Vehicle();
        Vehicle vehicle2 = new Vehicle();

        when(vehicleRepository.findAll()).thenReturn(List.of(vehicle1, vehicle2));
        when(vehicleMapper.entityToReadResponse(vehicle1)).thenReturn(new VehicleReadResponse());
        when(vehicleMapper.entityToReadResponse(vehicle2)).thenReturn(new VehicleReadResponse());

        List<VehicleReadResponse> responses = vehicleService.getAll();

        assertEquals(2, responses.size());
    }

    @Test
    void testDelete_Success() {
        Long vehicleId = 1L;
        Vehicle vehicle = new Vehicle();
        vehicle.setId(vehicleId);

        when(vehicleRepository.findById(vehicleId)).thenReturn(Optional.of(vehicle));

        vehicleService.delete(vehicleId);

        verify(vehicleRepository).deleteById(vehicleId);
    }

    @Test
    void testDelete_VehicleNotFound() {
        Long vehicleId = 1L;

        when(vehicleRepository.findById(vehicleId)).thenReturn(Optional.empty());

        assertThrows(VehicleNotFoundException.class,
                () -> vehicleService.delete(vehicleId));
    }
}