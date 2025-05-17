package az.hamburg.autoservice.mappers;

import az.hamburg.autoservice.domain.AppointmentMechanic;
import az.hamburg.autoservice.model.appointmentmechanic.request.AppointmentMechanicCreateRequest;
import az.hamburg.autoservice.model.appointmentmechanic.request.AppointmentMechanicUpdateRequest;
import az.hamburg.autoservice.model.appointmentmechanic.response.AppointmentMechanicCreateResponse;
import az.hamburg.autoservice.model.appointmentmechanic.response.AppointmentMechanicReadResponse;
import az.hamburg.autoservice.model.appointmentmechanic.response.AppointmentMechanicUpdateResponse;
import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;
import org.mapstruct.ReportingPolicy;

@Mapper(componentModel = "spring",unmappedTargetPolicy = ReportingPolicy.IGNORE,
        unmappedSourcePolicy = ReportingPolicy.IGNORE)
public interface AppointmentMechanicMapper {

    AppointmentMechanic createRequestToEntity(AppointmentMechanicCreateRequest createRequest);

    AppointmentMechanicCreateResponse entityToCreateResponse(AppointmentMechanic appointmentMechanic);

    AppointmentMechanicReadResponse entityToReadResponse(AppointmentMechanic appointmentMechanic);

    AppointmentMechanic updateRequestToEntity(@MappingTarget AppointmentMechanic appointmentMechanic , AppointmentMechanicUpdateRequest updateRequest);

    AppointmentMechanicUpdateResponse entityToUpdateResponse(AppointmentMechanic appointmentMechanic);
}
