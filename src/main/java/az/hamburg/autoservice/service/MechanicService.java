package az.hamburg.autoservice.service;

import az.hamburg.autoservice.model.mechanic.request.MechanicCreateRequest;
import az.hamburg.autoservice.model.mechanic.request.MechanicUpdateRequest;
import az.hamburg.autoservice.model.mechanic.response.MechanicCreateResponse;
import az.hamburg.autoservice.model.mechanic.response.MechanicReadResponse;
import az.hamburg.autoservice.model.mechanic.response.MechanicUpdateResponse;

import java.util.List;

public interface MechanicService {

    MechanicCreateResponse create(MechanicCreateRequest mechanicCreateRequest);

    MechanicUpdateResponse update(Long id, MechanicUpdateRequest updateRequest);

    List<MechanicReadResponse> getAll();

    MechanicReadResponse getId(Long id);

    void delete(Long id);

}
