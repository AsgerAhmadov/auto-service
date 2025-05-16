package az.hamburg.autoservice.service.impl;

import az.hamburg.autoservice.domain.AppointmentMechanic;
import az.hamburg.autoservice.exception.error.ErrorMessage;
import az.hamburg.autoservice.exception.handler.AppointmentMechanicNotFoundException;
import az.hamburg.autoservice.mappers.AppointmentMechanicMapper;
import az.hamburg.autoservice.model.appointmentmechanic.request.AppointmentMechanicCreateRequest;
import az.hamburg.autoservice.model.appointmentmechanic.request.AppointmentMechanicUpdateRequest;
import az.hamburg.autoservice.model.appointmentmechanic.response.AppointmentMechanicCreateResponse;
import az.hamburg.autoservice.model.appointmentmechanic.response.AppointmentMechanicReadResponse;
import az.hamburg.autoservice.model.appointmentmechanic.response.AppointmentMechanicUpdateResponse;
import az.hamburg.autoservice.repository.AppointmentMechanicRepository;
import az.hamburg.autoservice.service.AppointmentMechanicService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
@Slf4j
public class AppointmentMechanicServiceImpl implements AppointmentMechanicService {

    private final AppointmentMechanicRepository appointmentMechanicRepository;
    private final AppointmentMechanicMapper appointmentMechanicMapper;

    @Override
    public AppointmentMechanicCreateResponse create(AppointmentMechanicCreateRequest createRequest) {
        AppointmentMechanic entity = appointmentMechanicMapper.createRequestToEntity(createRequest);
        appointmentMechanicRepository.save(entity);
        return appointmentMechanicMapper.entityToCreateResponse(entity);
    }

    @Override
    public AppointmentMechanicUpdateResponse update(Long id , AppointmentMechanicUpdateRequest updateRequest) {
        AppointmentMechanic entity = appointmentMechanicRepository.findById(id)
                .orElseThrow(() -> new AppointmentMechanicNotFoundException(ErrorMessage.APPOINTMENT_MECHANIC_NOT_FOUND, HttpStatus.NOT_FOUND.name()));
        AppointmentMechanic update = appointmentMechanicMapper.updateRequestToEntity(entity, updateRequest);
        appointmentMechanicRepository.save(update);
        return appointmentMechanicMapper.entityToUpdateResponse(update);
    }

    @Override
    public AppointmentMechanicReadResponse getById(Long id) {
        AppointmentMechanic entity = appointmentMechanicRepository.findById(id)
                .orElseThrow(() -> new AppointmentMechanicNotFoundException(ErrorMessage.APPOINTMENT_MECHANIC_NOT_FOUND, HttpStatus.NOT_FOUND.name()));
        return appointmentMechanicMapper.entityToReadResponse(entity);
    }

    @Override
    public List<AppointmentMechanicReadResponse> getAll() {
        return appointmentMechanicRepository.findAll()
                .stream()
                .map(appointmentMechanicMapper::entityToReadResponse)
                .toList();
    }

    @Override
    public void delete(Long id) {
        AppointmentMechanic entity = appointmentMechanicRepository.findById(id)
                .orElseThrow(() -> new AppointmentMechanicNotFoundException(ErrorMessage.APPOINTMENT_MECHANIC_NOT_FOUND, HttpStatus.NOT_FOUND.name()));
        appointmentMechanicRepository.deleteById(entity.getId());
    }
}
