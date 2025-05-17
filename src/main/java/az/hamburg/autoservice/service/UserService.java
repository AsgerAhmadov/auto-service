package az.hamburg.autoservice.service;

import az.hamburg.autoservice.model.user.request.UserCreateRequest;
import az.hamburg.autoservice.model.user.request.UserLoginRequest;
import az.hamburg.autoservice.model.user.request.UserUpdateRequest;
import az.hamburg.autoservice.model.user.response.UserCreateResponse;
import az.hamburg.autoservice.model.user.response.UserReadResponse;
import az.hamburg.autoservice.model.user.response.UserUpdateResponse;

import java.util.List;

public interface UserService {

    UserCreateResponse create(UserCreateRequest createRequest);

    UserReadResponse getId(Long id);

    List<UserReadResponse> getAll();

    UserUpdateResponse update(Long id , UserUpdateRequest updateRequest);

    void delete (Long id);

     String loginUser(UserLoginRequest request);

}
