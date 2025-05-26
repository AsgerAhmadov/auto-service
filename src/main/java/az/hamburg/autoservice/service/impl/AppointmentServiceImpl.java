package az.hamburg.autoservice.service.impl;

import az.hamburg.autoservice.domain.*;
import az.hamburg.autoservice.exception.error.ErrorMessage;
import az.hamburg.autoservice.exception.handler.AppointmentNotFoundException;
import az.hamburg.autoservice.exception.handler.UserNotFoundException;
import az.hamburg.autoservice.exception.handler.UserUnAuthorizedException;
import az.hamburg.autoservice.exception.handler.VehicleNotFoundException;
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
import az.hamburg.autoservice.service.AppointmentService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
@Slf4j
public class AppointmentServiceImpl implements AppointmentService {

    private final AppointmentRepository appointmentRepository;
    private final AppointmentMapper appointmentMapper;
    private final VehicleRepository vehicleRepository;
    private final UserRepository userRepository;

    @Override
    public AppointmentCreateResponse create(Long vehicleId,AppointmentCreateRequest createRequest) {

        Vehicle vehicle = vehicleRepository.findById(vehicleId)
                .orElseThrow(()-> new VehicleNotFoundException(ErrorMessage.VEHICLE_NOT_FOUND,HttpStatus.NOT_FOUND.name()));

        Appointment entity = appointmentMapper.createRequestToEntity(createRequest);
        entity.setVehicleId(vehicle.getId());
        entity.setStatus(RequestStatus.PENDING);
        appointmentRepository.save(entity);
        return appointmentMapper.entityToCreateResponse(entity);
    }

    @Override
    public AppointmentUpdateResponse update(Long id , AppointmentUpdateRequest updateRequest) {
        Appointment entity = appointmentRepository.findById(id)
                .orElseThrow(() -> new AppointmentNotFoundException(ErrorMessage.APPOINTMENT_NOT_FOUND, HttpStatus.NOT_FOUND.name()));
        Appointment update = appointmentMapper.updateRequestToEntity(entity, updateRequest);
        appointmentRepository.save(update);
        return appointmentMapper.entityToUpdateResponse(update);
    }

    @Override
    public AppointmentReadResponse getById(Long id) {
        Appointment entity = appointmentRepository.findById(id)
                .orElseThrow(() -> new AppointmentNotFoundException(ErrorMessage.APPOINTMENT_NOT_FOUND, HttpStatus.NOT_FOUND.name()));
        return appointmentMapper.entityToReadResponse(entity);
    }

    @Override
    public List<AppointmentReadResponse> getAll() {
        return appointmentRepository.findAll()
                .stream()
                .map(appointmentMapper::entityToReadResponse)
                .toList();
    }

    @Override
    public void delete(Long id) {
        Appointment entity = appointmentRepository.findById(id)
                .orElseThrow(() -> new AppointmentNotFoundException(ErrorMessage.APPOINTMENT_NOT_FOUND, HttpStatus.NOT_FOUND.name()));
        appointmentRepository.deleteById(entity.getId());
    }

    @Override
    public List<AppointmentReadResponse> getAllStatusPending() {
        return appointmentRepository.getAllAppointmentByStatus(RequestStatus.PENDING)
                .stream()
                .map(appointmentMapper::entityToReadResponse)
                .toList();
    }

    @Override
    public AppointmentStatusUpdateResponse statusUpdate( Long userId, Long appointmentId, boolean statusChange) {

        User changerUser = userRepository.findById(userId)
                .orElseThrow(() -> new UserNotFoundException(ErrorMessage.USER_NOT_FOUND, HttpStatus.NOT_FOUND.name()));

        Appointment foundedAppointment = appointmentRepository.findById(appointmentId)
                .orElseThrow(() -> new AppointmentNotFoundException(ErrorMessage.APPOINTMENT_NOT_FOUND, HttpStatus.NOT_FOUND.name()));


        if (!changerUser.getRoleType().equals(RoleType.MODERATOR)) {
            throw new UserUnAuthorizedException(ErrorMessage.USER_UNAUTHORIZED, HttpStatus.UNAUTHORIZED.name());
        }

        foundedAppointment.setStatusChange(statusChange);


        appointmentRepository.save(foundedAppointment);

        return appointmentMapper.entityToAppointmentStatusUpdateResponse(foundedAppointment);
    }





}
