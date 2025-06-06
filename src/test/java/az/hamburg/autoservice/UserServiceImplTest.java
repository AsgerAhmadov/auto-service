package az.hamburg.autoservice;

import az.hamburg.autoservice.domain.RoleType;
import az.hamburg.autoservice.domain.User;
import az.hamburg.autoservice.exception.handler.EmailAlreadyExistsException;
import az.hamburg.autoservice.exception.handler.UserUnAuthorizedException;
import az.hamburg.autoservice.exception.handler.WrongPhoneNumberException;
import az.hamburg.autoservice.mappers.UserMapper;
import az.hamburg.autoservice.model.user.request.UserCreateRequest;
import az.hamburg.autoservice.model.user.request.UserLoginRequest;
import az.hamburg.autoservice.model.user.request.UserUpdateRequest;
import az.hamburg.autoservice.model.user.response.UserCreateResponse;
import az.hamburg.autoservice.model.user.response.UserReadResponse;
import az.hamburg.autoservice.model.user.response.UserRoleUpdateResponse;
import az.hamburg.autoservice.model.user.response.UserUpdateResponse;
import az.hamburg.autoservice.repository.UserRepository;
import az.hamburg.autoservice.service.impl.UserServiceImpl;
import az.hamburg.autoservice.util.UserUtil;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.util.Optional;
import java.util.Collections;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;
@ExtendWith(MockitoExtension.class)
public class UserServiceImplTest {

    @InjectMocks
    private UserServiceImpl userService;

    @Mock
    private UserRepository userRepository;

    @Mock
    private UserMapper userMapper;

    @Mock
    private PasswordEncoder passwordEncoder;

    @Test
    void testCreateUser_Success() {
        UserCreateRequest request = UserUtil.createRequest();
        User user = UserUtil.user(RoleType.USER);

        when(userRepository.findAll()).thenReturn(List.of());
        when(userMapper.createRequestToEntity(request)).thenReturn(user);
        when(passwordEncoder.encode(request.getPassword())).thenReturn("encoded_password");
        when(userMapper.entityToCreateResponse(user)).thenReturn(UserUtil.userCreateResponse());

        UserCreateResponse response = userService.create(request);

        assertNotNull(response);
        verify(userRepository).save(user);
    }

    @Test
    void testCreateUser_InvalidPhone() {
        UserCreateRequest request = UserUtil.createRequest();
        request.setPhone("+1234512345");

        assertThrows(WrongPhoneNumberException.class, () -> userService.create(request));
    }

    @Test
    void testCreateUser_EmailExists() {
        UserCreateRequest request = UserUtil.createRequest();
        User existing = UserUtil.user(RoleType.USER);

        when(userRepository.findAll()).thenReturn(List.of(existing));

        assertThrows(EmailAlreadyExistsException.class, () -> userService.create(request));
    }

    @Test
    void testGetById_Success() {
        User user = UserUtil.user(RoleType.USER);
        when(userRepository.findById(1L)).thenReturn(Optional.of(user));
        when(userMapper.entityToReadResponse(user)).thenReturn(UserUtil.userReadResponse());

        UserReadResponse response = userService.getId(1L);

        assertNotNull(response);
    }

    @Test
    void testGetAllUsers() {
        User user = UserUtil.user(RoleType.USER);
        when(userRepository.findAll()).thenReturn(List.of(user));
        when(userMapper.entityToReadResponse(user)).thenReturn(UserUtil.userReadResponse());

        List<UserReadResponse> result = userService.getAll();

        assertEquals(1, result.size());
    }

    @Test
    void testUpdateUser_Success() {
        User user = UserUtil.user(RoleType.USER);
        UserUpdateRequest updateRequest = UserUtil.updateRequest();

        when(userRepository.findById(1L)).thenReturn(Optional.of(user));
        when(userMapper.updateRequestToEntity(user, updateRequest)).thenReturn(user);
        when(userMapper.entityToUpdateResponse(user)).thenReturn(UserUtil.userUpdateResponse());

        UserUpdateResponse response = userService.update(1L, updateRequest);

        assertNotNull(response);
        verify(userRepository).save(user);
    }

    @Test
    void testDeleteUser_AsAdmin() {
        User admin = UserUtil.user(RoleType.ADMIN);
        when(userRepository.findById(1L)).thenReturn(Optional.of(admin));

        userService.delete(1L);

        verify(userRepository).deleteById(1L);
    }

    @Test
    void testDeleteUser_Unauthorized() {
        User user = UserUtil.user(RoleType.USER);
        when(userRepository.findById(1L)).thenReturn(Optional.of(user));

        assertThrows(UserUnAuthorizedException.class, () -> userService.delete(1L));
    }

    @Test
    void testLoginUser_Success() {
        User user = UserUtil.user(RoleType.USER);
        when(userRepository.findByUsername("johnny")).thenReturn(Optional.of(user));
        when(passwordEncoder.matches("Password123!", user.getPassword())).thenReturn(true);

        String result = userService.loginUser(UserUtil.loginRequest("johnny", "Password123!"));
        assertEquals("login successful", result);
    }

    @Test
    void testLoginUser_WrongPassword() {
        User user = UserUtil.user(RoleType.USER);
        when(userRepository.findByUsername("johnny")).thenReturn(Optional.of(user));
        when(passwordEncoder.matches("wrong", user.getPassword())).thenReturn(false);

        String result = userService.loginUser(UserUtil.loginRequest("johnny", "wrong"));
        assertEquals("Password is incorrect", result);
    }

    @Test
    void testRoleUpdate_Success() {
        User changer = UserUtil.user(RoleType.ADMIN);
        User target = UserUtil.user(RoleType.USER);
        target.setId(2L);

        when(userRepository.findById(2L)).thenReturn(Optional.of(target));
        when(userRepository.findById(1L)).thenReturn(Optional.of(changer));
        when(userMapper.entityToUserRoleUpdateResponse(target)).thenReturn(UserUtil.userRoleUpdateResponse());

        UserRoleUpdateResponse response = userService.roleUpdate(1L, 2L, RoleType.MODERATOR);

        assertNotNull(response);
        verify(userRepository).save(target);
    }

    @Test
    void testRoleUpdate_Unauthorized() {
        User changer = UserUtil.user(RoleType.USER);
        User target = UserUtil.user(RoleType.USER);

        when(userRepository.findById(2L)).thenReturn(Optional.of(target));
        when(userRepository.findById(1L)).thenReturn(Optional.of(changer));

        assertThrows(UserUnAuthorizedException.class, () -> userService.roleUpdate(1L, 2L, RoleType.ADMIN));
    }
}


