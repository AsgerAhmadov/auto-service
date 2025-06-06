package az.hamburg.autoservice.util;

import az.hamburg.autoservice.domain.RoleType;
import az.hamburg.autoservice.domain.User;
import az.hamburg.autoservice.model.user.request.UserCreateRequest;
import az.hamburg.autoservice.model.user.request.UserLoginRequest;
import az.hamburg.autoservice.model.user.request.UserUpdateRequest;
import az.hamburg.autoservice.model.user.response.UserCreateResponse;
import az.hamburg.autoservice.model.user.response.UserReadResponse;
import az.hamburg.autoservice.model.user.response.UserRoleUpdateResponse;
import az.hamburg.autoservice.model.user.response.UserUpdateResponse;

import java.time.LocalDateTime;

public class UserUtil {

    private UserUtil() {

    }

    public static UserCreateRequest createRequest() {
        UserCreateRequest request = new UserCreateRequest();
        request.setUsername("johnny");
        request.setPassword("Password123!");
        request.setName("John Doe");
        request.setPhone("+994501234567");
        request.setEmail("john.doe@example.com");
        return request;
    }

    public static UserUpdateRequest updateRequest() {
        UserUpdateRequest request = new UserUpdateRequest();
        request.setUsername("johnny_updated");
        request.setPassword("NewPass123!");
        request.setName("John Updated");
        request.setPhone("+994551234567");
        request.setEmail("john.updated@example.com");
        return request;
    }

    public static User user(RoleType role) {
        User user = new User();
        user.setId(1L);
        user.setUsername("johnny");
        user.setPassword("encoded_password");
        user.setName("John Doe");
        user.setPhone("+994501234567");
        user.setEmail("john.doe@example.com");
        user.setRoleType(role);
        return user;
    }

    public static UserLoginRequest loginRequest(String username, String password) {
        UserLoginRequest request = new UserLoginRequest();
        request.setUsername(username);
        request.setPassword(password);
        return request;
    }

    public static UserCreateResponse userCreateResponse() {
        UserCreateResponse response = new UserCreateResponse();
        response.setId(1L);
        response.setUsername("testuser");
        response.setPassword("encryptedPassword");
        response.setName("Test User");
        response.setPhone("+994501234567");
        response.setEmail("testuser@example.com");
        response.setCreated(LocalDateTime.now().minusDays(1));
        return response;
    }


    public static UserReadResponse userReadResponse() {
        UserReadResponse response = new UserReadResponse();
        response.setId(1L);
        response.setUsername("testuser");
        response.setPassword("encryptedPassword");
        response.setName("Test User");
        response.setPhone("+994501234567");
        response.setEmail("testuser@example.com");
        response.setRoleType(RoleType.USER);
        response.setCreated(LocalDateTime.now().minusDays(2));
        response.setModified(LocalDateTime.now().minusDays(1));
        return response;
    }

    public static UserUpdateResponse userUpdateResponse() {
        UserUpdateResponse response = new UserUpdateResponse();
        response.setId(1L);
        response.setUsername("updatedUser");
        response.setPassword("updatedEncryptedPassword");
        response.setName("Updated Name");
        response.setPhone("+994701234567");
        response.setEmail("updateduser@example.com");
        response.setModified(LocalDateTime.now());
        return response;
    }

    public static UserRoleUpdateResponse userRoleUpdateResponse() {
        UserRoleUpdateResponse response = new UserRoleUpdateResponse();
        response.setId(1L);
        response.setRoleType(RoleType.ADMIN);
        response.setModified(LocalDateTime.now());
        return response;
    }
}
