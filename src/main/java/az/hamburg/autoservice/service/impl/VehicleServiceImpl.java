package az.hamburg.autoservice.service.impl;

import az.hamburg.autoservice.domain.User;
import az.hamburg.autoservice.domain.Vehicle;
import az.hamburg.autoservice.exception.error.ErrorMessage;
import az.hamburg.autoservice.exception.handler.EmailAlreadyExistsException;
import az.hamburg.autoservice.exception.handler.PlateNumberAlreadyExistsException;
import az.hamburg.autoservice.exception.handler.UserNotFoundException;
import az.hamburg.autoservice.exception.handler.VehicleNotFoundException;
import az.hamburg.autoservice.mappers.VehicleMapper;
import az.hamburg.autoservice.model.vehicle.request.VehicleCreateRequest;
import az.hamburg.autoservice.model.vehicle.request.VehicleUpdateRequest;
import az.hamburg.autoservice.model.vehicle.response.VehicleCreateResponse;
import az.hamburg.autoservice.model.vehicle.response.VehicleReadResponse;
import az.hamburg.autoservice.model.vehicle.response.VehicleUpdateResponse;
import az.hamburg.autoservice.repository.UserRepository;
import az.hamburg.autoservice.repository.VehicleRepository;
import az.hamburg.autoservice.service.UserService;
import az.hamburg.autoservice.service.VehicleService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@Slf4j
public class VehicleServiceImpl implements VehicleService {

    private final VehicleRepository vehicleRepository;
    private final VehicleMapper vehicleMapper;
    private final UserRepository userRepository;


    @Override
    public VehicleCreateResponse create(Long userId ,VehicleCreateRequest createRequest) {

        User user = userRepository.findById(userId)
                .orElseThrow(()-> new  UserNotFoundException(ErrorMessage.USER_NOT_FOUND,HttpStatus.NOT_FOUND.name()));

        List<String> allPlateNumbers = vehicleRepository.findAll()
                .stream()
                .map(Vehicle::getPlateNumber)
                .collect(Collectors.toList());
        if (allPlateNumbers.contains(createRequest.getPlateNumber())){
            throw new PlateNumberAlreadyExistsException(ErrorMessage.PLATE_NUMBER_ALREADY_EXISTS, HttpStatus.BAD_REQUEST.name());
        }
        Vehicle entity = vehicleMapper.createRequestToEntity(createRequest);
        entity.setUserId(user.getId());
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
