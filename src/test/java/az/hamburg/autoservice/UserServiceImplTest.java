package az.hamburg.autoservice;

import az.hamburg.autoservice.domain.RoleType;
import az.hamburg.autoservice.domain.User;
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
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.util.Optional;
import java.util.Collections;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class UserServiceImplTest {

    @Mock
    private UserRepository userRepository;

    @Mock
    private UserMapper userMapper;

    @Mock
    private PasswordEncoder passwordEncoder;

    @InjectMocks
    private UserServiceImpl userService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testCreate_Success() {
        UserCreateRequest request = new UserCreateRequest();
        request.setPhone("+994501234567");
        request.setEmail("test@example.com");
        request.setPassword("plainPass");

        when(userRepository.findAll()).thenReturn(Collections.emptyList());

        User userEntity = new User();
        when(userMapper.createRequestToEntity(request)).thenReturn(userEntity);
        when(passwordEncoder.encode("plainPass")).thenReturn("encodedPass");
        when(userRepository.save(any(User.class))).thenReturn(userEntity);
        UserCreateResponse expectedResponse = new UserCreateResponse();
        when(userMapper.entityToCreateResponse(userEntity)).thenReturn(expectedResponse);

        UserCreateResponse response = userService.create(request);

        assertNotNull(response);
        verify(userRepository).save(any(User.class));
    }

    @Test
    void testGetId_Success() {
        User user = new User();
        user.setId(1L);
        when(userRepository.findById(1L)).thenReturn(Optional.of(user));
        UserReadResponse expectedResponse = new UserReadResponse();
        when(userMapper.entityToReadResponse(user)).thenReturn(expectedResponse);

        UserReadResponse response = userService.getId(1L);

        assertNotNull(response);
        verify(userRepository).findById(1L);
    }

    @Test
    void testGetAll_Success() {
        List<User> users = List.of(new User());
        when(userRepository.findAll()).thenReturn(users);
        when(userMapper.entityToReadResponse(any(User.class))).thenReturn(new UserReadResponse());

        List<UserReadResponse> responses = userService.getAll();

        assertEquals(1, responses.size());
        verify(userRepository).findAll();
    }

    @Test
    void testUpdate_Success() {
        UserUpdateRequest request = new UserUpdateRequest();
        User existingUser = new User();
        when(userRepository.findById(1L)).thenReturn(Optional.of(existingUser));
        User updatedUser = new User();
        when(userMapper.updateRequestToEntity(existingUser, request)).thenReturn(updatedUser);
        when(userRepository.save(updatedUser)).thenReturn(updatedUser);
        UserUpdateResponse expectedResponse = new UserUpdateResponse();
        when(userMapper.entityToUpdateResponse(updatedUser)).thenReturn(expectedResponse);

        UserUpdateResponse response = userService.update(1L, request);

        assertNotNull(response);
        verify(userRepository).save(updatedUser);
    }

    @Test
    void testDelete_Success() {
        Long userId = 1L;

        User adminUser = new User();
        adminUser.setId(userId);
        adminUser.setRoleType(RoleType.ADMIN);

        when(userRepository.findById(userId)).thenReturn(Optional.of(adminUser));

        userService.delete(userId);

        verify(userRepository, times(1)).deleteById(userId);
    }
    @Test
    void testLoginUser_Success() {
        String username = "testuser";
        String rawPassword = "password";
        String encodedPassword = "$2a$10$encodedpassword";

        User user = new User();
        user.setUsername(username);
        user.setPassword(encodedPassword);

        UserLoginRequest request = new UserLoginRequest();
        request.setUsername(username);
        request.setPassword(rawPassword);

        when(userRepository.findByUsername(username)).thenReturn(Optional.of(user));
        when(passwordEncoder.matches(rawPassword, encodedPassword)).thenReturn(true);

        String result = userService.loginUser(request);

        assertEquals("login successful", result);
    }

    @Test
    void testRoleUpdate_Success() {
        Long changerId = 1L;
        Long targetId = 2L;
        RoleType newRole = RoleType.MODERATOR;

        User changerUser = new User();
        changerUser.setId(changerId);
        changerUser.setRoleType(RoleType.ADMIN);

        User targetUser = new User();
        targetUser.setId(targetId);
        targetUser.setRoleType(RoleType.USER);

        UserRoleUpdateResponse expectedResponse = new UserRoleUpdateResponse();

        when(userRepository.findById(changerId)).thenReturn(Optional.of(changerUser));
        when(userRepository.findById(targetId)).thenReturn(Optional.of(targetUser));
        when(userRepository.save(any(User.class))).thenReturn(targetUser);
        when(userMapper.entityToUserRoleUpdateResponse(targetUser)).thenReturn(expectedResponse);

        UserRoleUpdateResponse response = userService.roleUpdate(changerId, targetId, newRole);

        assertNotNull(response);
        assertEquals(expectedResponse, response);

        verify(userRepository, times(1)).save(targetUser);
    }
}


