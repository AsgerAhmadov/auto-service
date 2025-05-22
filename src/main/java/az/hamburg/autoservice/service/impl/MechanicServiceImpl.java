package az.hamburg.autoservice.service.impl;

import az.hamburg.autoservice.domain.Mechanic;
import az.hamburg.autoservice.domain.RoleType;
import az.hamburg.autoservice.domain.User;
import az.hamburg.autoservice.exception.error.ErrorMessage;
import az.hamburg.autoservice.exception.handler.EmailAlreadyExistsException;
import az.hamburg.autoservice.exception.handler.MechanicNotFoundException;
import az.hamburg.autoservice.exception.handler.UserNotFoundException;
import az.hamburg.autoservice.mappers.MechanicMapper;
import az.hamburg.autoservice.model.mechanic.request.MechanicCreateRequest;
import az.hamburg.autoservice.model.mechanic.request.MechanicUpdateRequest;
import az.hamburg.autoservice.model.mechanic.response.MechanicCreateResponse;
import az.hamburg.autoservice.model.mechanic.response.MechanicReadResponse;
import az.hamburg.autoservice.model.mechanic.response.MechanicUpdateResponse;
import az.hamburg.autoservice.repository.MechanicRepository;
import az.hamburg.autoservice.service.MechanicService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
@Slf4j
public class MechanicServiceImpl implements MechanicService {

    private final MechanicRepository mechanicRepository;
    private final MechanicMapper mechanicMapper;


    @Override
    public MechanicCreateResponse create(MechanicCreateRequest mechanicCreateRequest) {

        List<String> allEmails = mechanicRepository.findAll()
                .stream().map(Mechanic::getEmail)
                .toList();
        if (allEmails.contains(mechanicCreateRequest.getEmail())){
            throw new EmailAlreadyExistsException(ErrorMessage.EMAIL_ALREADY_EXISTS, HttpStatus.BAD_REQUEST.name());
        }

        Mechanic mechanic = mechanicMapper.createRequestToEntity(mechanicCreateRequest);
        mechanicRepository.save(mechanic);
        return mechanicMapper.entityToCreateResponse(mechanic);

    }

    @Override
    public MechanicUpdateResponse update(Long id, MechanicUpdateRequest updateRequest) {

        Mechanic entity = mechanicRepository
                .findById(id).orElseThrow(() -> new MechanicNotFoundException(ErrorMessage.MECHANIC_NOT_FOUND, HttpStatus.NOT_FOUND.name()));
        Mechanic savedMechanic = mechanicMapper.updateRequestToEntity(entity,updateRequest);
        mechanicRepository.save(savedMechanic);
        return mechanicMapper.entityToUpdateResponse(savedMechanic);

    }

    @Override
    public List<MechanicReadResponse> getAll() {

        List<Mechanic> mechanicList = mechanicRepository.findAll();
         return mechanicList.stream()
                 .map(mechanicMapper::entityToReadResponse)
                 .toList();

    }

    @Override
    public MechanicReadResponse getId(Long id) {

        Mechanic foundedMechanic = mechanicRepository.findById(id)
                .orElseThrow(() -> new MechanicNotFoundException(ErrorMessage.MECHANIC_NOT_FOUND, HttpStatus.NOT_FOUND.name()));
        return mechanicMapper.entityToReadResponse(foundedMechanic);

    }

    @Override
    public void delete(Long id) {

        Mechanic mechanic = mechanicRepository
                .findById(id).orElseThrow(() -> new MechanicNotFoundException(ErrorMessage.MECHANIC_NOT_FOUND, HttpStatus.NOT_FOUND.name()));
        mechanicRepository.delete(mechanic);

    }
}
