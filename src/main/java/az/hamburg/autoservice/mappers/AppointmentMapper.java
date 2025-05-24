package az.hamburg.autoservice.mappers;

import az.hamburg.autoservice.domain.Appointment;
import az.hamburg.autoservice.model.appointment.request.AppointmentCreateRequest;
import az.hamburg.autoservice.model.appointment.request.AppointmentUpdateRequest;
import az.hamburg.autoservice.model.appointment.response.AppointmentCreateResponse;
import az.hamburg.autoservice.model.appointment.response.AppointmentReadResponse;
import az.hamburg.autoservice.model.appointment.response.AppointmentStatusUpdateResponse;
import az.hamburg.autoservice.model.appointment.response.AppointmentUpdateResponse;
import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;
import org.mapstruct.ReportingPolicy;

@Mapper(componentModel = "spring",unmappedTargetPolicy = ReportingPolicy.IGNORE,
        unmappedSourcePolicy = ReportingPolicy.IGNORE)
public interface AppointmentMapper {

    Appointment createRequestToEntity(AppointmentCreateRequest createRequest);

    AppointmentCreateResponse entityToCreateResponse(Appointment appointment);

    AppointmentReadResponse entityToReadResponse(Appointment appointment);

    Appointment updateRequestToEntity(@MappingTarget Appointment appointment , AppointmentUpdateRequest updateRequest);

    AppointmentUpdateResponse entityToUpdateResponse(Appointment appointment);

}
