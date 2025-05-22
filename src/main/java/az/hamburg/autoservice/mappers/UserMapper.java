package az.hamburg.autoservice.mappers;

import az.hamburg.autoservice.domain.User;
import az.hamburg.autoservice.model.user.request.UserCreateRequest;
import az.hamburg.autoservice.model.user.request.UserUpdateRequest;
import az.hamburg.autoservice.model.user.response.*;
import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;
import org.mapstruct.ReportingPolicy;

@Mapper(componentModel = "spring",unmappedTargetPolicy = ReportingPolicy.IGNORE,
        unmappedSourcePolicy = ReportingPolicy.IGNORE)

public interface UserMapper {

    User createRequestToEntity(UserCreateRequest createRequest);

    UserCreateResponse entityToCreateResponse(User user);

    UserReadResponse entityToReadResponse(User user);

    User updateRequestToEntity(@MappingTarget User user, UserUpdateRequest updateRequest);

    UserUpdateResponse entityToUpdateResponse(User user);

    UserRoleUpdateResponse entityToUserRoleUpdateResponse(User user);

    UserStatusUpdateResponse entityToUserStatusUpdateResponse(User user);

}
