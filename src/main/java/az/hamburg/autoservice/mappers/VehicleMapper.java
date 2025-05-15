package az.hamburg.autoservice.mappers;

import az.hamburg.autoservice.domain.Vehicle;
import az.hamburg.autoservice.model.vehicle.request.VehicleCreateRequest;
import az.hamburg.autoservice.model.vehicle.request.VehicleUpdateRequest;
import az.hamburg.autoservice.model.vehicle.response.VehicleCreateResponse;
import az.hamburg.autoservice.model.vehicle.response.VehicleReadResponse;
import az.hamburg.autoservice.model.vehicle.response.VehicleUpdateResponse;
import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;
import org.mapstruct.ReportingPolicy;

@Mapper(componentModel = "spring",unmappedTargetPolicy = ReportingPolicy.IGNORE,
        unmappedSourcePolicy = ReportingPolicy.IGNORE)
public interface VehicleMapper {

    Vehicle createRequestToEntity(VehicleCreateRequest createRequest);

    VehicleCreateResponse entityToCreateResponse(Vehicle vehicle);

    VehicleReadResponse entityToReadResponse(Vehicle vehicle);

    Vehicle updateRequestToEntity(@MappingTarget Vehicle vehicle ,VehicleUpdateRequest updateRequest);

    VehicleUpdateResponse entityToUpdateResponse(Vehicle vehicle);
}
