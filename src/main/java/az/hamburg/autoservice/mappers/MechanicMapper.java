package az.hamburg.autoservice.mappers;

import az.hamburg.autoservice.domain.Mechanic;
import az.hamburg.autoservice.model.mechanic.request.MechanicCreateRequest;
import az.hamburg.autoservice.model.mechanic.request.MechanicUpdateRequest;
import az.hamburg.autoservice.model.mechanic.response.MechanicCreateResponse;
import az.hamburg.autoservice.model.mechanic.response.MechanicReadResponse;
import az.hamburg.autoservice.model.mechanic.response.MechanicUpdateResponse;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;

@Mapper(componentModel = "spring",unmappedTargetPolicy = ReportingPolicy.IGNORE,
        unmappedSourcePolicy = ReportingPolicy.IGNORE)

public interface MechanicMapper {

    Mechanic createRequestToEntity(MechanicCreateRequest createRequest);

    MechanicCreateResponse entityToCreateResponse(Mechanic mechanic);

    MechanicReadResponse entityToReadResponse(Mechanic mechanic);

    Mechanic updateRequestToEntity(MechanicUpdateRequest updateRequest);

    MechanicUpdateResponse entityToUpdateResponse(Mechanic mechanic);

}
