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
import az.hamburg.autoservice.util.UserUtil;
import az.hamburg.autoservice.util.VehicleUtil;
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
public class VehicleServiceImplTest {

    @InjectMocks
    private VehicleServiceImpl vehicleService;

    @Mock
    private VehicleRepository vehicleRepository;

    @Mock
    private VehicleMapper vehicleMapper;

    @Mock
    private UserRepository userRepository;

    @Test
    void create_ShouldReturnResponse_WhenValidRequest() {
        VehicleCreateRequest request = VehicleUtil.createRequest();
        Vehicle vehicle = VehicleUtil.vehicle();
        User user = VehicleUtil.user();
        VehicleCreateResponse response = VehicleUtil.vehicleCreateResponse();

        when(userRepository.findById(1L)).thenReturn(Optional.of(user));
        when(vehicleRepository.findAll()).thenReturn(List.of());
        when(vehicleMapper.createRequestToEntity(request)).thenReturn(vehicle);
        when(vehicleMapper.entityToCreateResponse(vehicle)).thenReturn(response);

        VehicleCreateResponse result = vehicleService.create(1L, request);

        assertNotNull(result);
        verify(vehicleRepository).save(vehicle);
    }

    @Test
    void update_ShouldReturnResponse_WhenValidRequest() {
        Vehicle vehicle = VehicleUtil.vehicle();
        VehicleUpdateRequest updateRequest = VehicleUtil.updateRequest();
        Vehicle updated = VehicleUtil.vehicle();
        updated.setBrand(updateRequest.getBrand());
        updated.setModel(updateRequest.getModel());
        updated.setPlateNumber(updateRequest.getPlateNumber());
        VehicleUpdateResponse response = VehicleUtil.vehicleUpdateResponse();

        when(vehicleRepository.findById(1L)).thenReturn(Optional.of(vehicle));
        when(vehicleMapper.updateRequestToEntity(vehicle, updateRequest)).thenReturn(updated);
        when(vehicleMapper.entityToUpdateResponse(updated)).thenReturn(response);

        VehicleUpdateResponse result = vehicleService.update(1L, updateRequest);

        assertNotNull(result);
        verify(vehicleRepository).save(updated);
    }

    @Test
    void getById_ShouldReturnResponse_WhenFound() {
        Vehicle vehicle = VehicleUtil.vehicle();
        VehicleReadResponse response = VehicleUtil.vehicleReadResponse();

        when(vehicleRepository.findById(1L)).thenReturn(Optional.of(vehicle));
        when(vehicleMapper.entityToReadResponse(vehicle)).thenReturn(response);

        VehicleReadResponse result = vehicleService.getById(1L);

        assertNotNull(result);
    }

    @Test
    void getAll_ShouldReturnListOfResponses() {
        Vehicle vehicle = VehicleUtil.vehicle();
        VehicleReadResponse response = VehicleUtil.vehicleReadResponse();

        when(vehicleRepository.findAll()).thenReturn(List.of(vehicle));
        when(vehicleMapper.entityToReadResponse(vehicle)).thenReturn(response);

        List<VehicleReadResponse> result = vehicleService.getAll();

        assertEquals(1, result.size());
    }

    @Test
    void delete_ShouldRemoveVehicle_WhenExists() {
        Vehicle vehicle = VehicleUtil.vehicle();

        when(vehicleRepository.findById(1L)).thenReturn(Optional.of(vehicle));

        vehicleService.delete(1L);

        verify(vehicleRepository).deleteById(vehicle.getId());
    }
}