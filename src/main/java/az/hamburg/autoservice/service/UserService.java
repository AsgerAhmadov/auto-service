package az.hamburg.autoservice.service;

import az.hamburg.autoservice.domain.RoleType;
import az.hamburg.autoservice.model.user.request.UserCreateRequest;
import az.hamburg.autoservice.model.user.request.UserLoginRequest;
import az.hamburg.autoservice.model.user.request.UserUpdateRequest;
import az.hamburg.autoservice.model.user.response.*;

import java.util.List;

public interface UserService {

    UserCreateResponse create(UserCreateRequest createRequest);

    UserReadResponse getId(Long id);

    List<UserReadResponse> getAll();

    UserUpdateResponse update(Long id , UserUpdateRequest updateRequest);

    void delete (Long id);

     String loginUser(UserLoginRequest request);

    UserRoleUpdateResponse roleUpdate( Long changerId, Long id, RoleType roleType);

}
