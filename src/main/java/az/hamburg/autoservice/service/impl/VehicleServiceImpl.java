package az.hamburg.autoservice.service.impl;

import az.hamburg.autoservice.domain.Vehicle;
import az.hamburg.autoservice.exception.error.ErrorMessage;
import az.hamburg.autoservice.exception.handler.VehicleNotFoundException;
import az.hamburg.autoservice.mappers.VehicleMapper;
import az.hamburg.autoservice.model.vehicle.request.VehicleCreateRequest;
import az.hamburg.autoservice.model.vehicle.request.VehicleUpdateRequest;
import az.hamburg.autoservice.model.vehicle.response.VehicleCreateResponse;
import az.hamburg.autoservice.model.vehicle.response.VehicleReadResponse;
import az.hamburg.autoservice.model.vehicle.response.VehicleUpdateResponse;
import az.hamburg.autoservice.repository.VehicleRepository;
import az.hamburg.autoservice.service.VehicleService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
@Slf4j
public class VehicleServiceImpl implements VehicleService {

    private final VehicleRepository vehicleRepository;
    private final VehicleMapper vehicleMapper;


    @Override
    public VehicleCreateResponse create(VehicleCreateRequest createRequest) {
        Vehicle entity = vehicleMapper.createRequestToEntity(createRequest);
        vehicleRepository.save(entity);
        return vehicleMapper.entityToCreateResponse(entity);
    }

    @Override
    public VehicleUpdateResponse update(Long id , VehicleUpdateRequest updateRequest) {
        Vehicle entity = vehicleRepository.findById(id)
                .orElseThrow(() -> new VehicleNotFoundException(ErrorMessage.VEHICLE_NOT_FOUND, HttpStatus.NOT_FOUND.name()));
        Vehicle update = vehicleMapper.updateRequestToEntity(entity, updateRequest);
        vehicleRepository.save(update);
        return vehicleMapper.entityToUpdateResponse(update);
    }

    @Override
    public VehicleReadResponse getById(Long id) {
        Vehicle entity = vehicleRepository.findById(id)
                .orElseThrow(() -> new VehicleNotFoundException(ErrorMessage.VEHICLE_NOT_FOUND, HttpStatus.NOT_FOUND.name()));
        return vehicleMapper.entityToReadResponse(entity);
    }

    @Override
    public List<VehicleReadResponse> getAll() {
        return vehicleRepository.findAll()
                .stream()
                .map(vehicleMapper::entityToReadResponse)
                .toList();
    }

    @Override
    public void delete(Long id) {
        Vehicle entity = vehicleRepository.findById(id)
                .orElseThrow(() -> new VehicleNotFoundException(ErrorMessage.VEHICLE_NOT_FOUND, HttpStatus.NOT_FOUND.name()));
        vehicleRepository.deleteById(entity.getId());
    }

}
