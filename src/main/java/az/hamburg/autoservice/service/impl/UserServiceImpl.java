package az.hamburg.autoservice.service.impl;

import az.hamburg.autoservice.domain.RoleType;
import az.hamburg.autoservice.domain.User;
import az.hamburg.autoservice.exception.error.ErrorMessage;
import az.hamburg.autoservice.exception.handler.EmailAlreadyExistsException;
import az.hamburg.autoservice.exception.handler.UserNotFoundException;
import az.hamburg.autoservice.exception.handler.WrongPhoneNumberException;
import az.hamburg.autoservice.mappers.UserMapper;
import az.hamburg.autoservice.model.user.request.UserCreateRequest;
import az.hamburg.autoservice.model.user.request.UserLoginRequest;
import az.hamburg.autoservice.model.user.request.UserUpdateRequest;
import az.hamburg.autoservice.model.user.response.UserCreateResponse;
import az.hamburg.autoservice.model.user.response.UserReadResponse;
import az.hamburg.autoservice.model.user.response.UserUpdateResponse;
import az.hamburg.autoservice.repository.UserRepository;
import az.hamburg.autoservice.service.UserService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@Slf4j
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;
    private final UserMapper userMapper;

    @Override
    public UserCreateResponse create(UserCreateRequest createRequest) {

        String phoneNumber = createRequest.getPhone();
        String checkNumber = phoneNumber.substring(0, 4);
        List<String> operatorBeginningsOfAzerbaijan = Arrays.asList("50", "51", "55", "99", "70", "77", "60");
        String checkOperatorNumber = phoneNumber.substring(4, 6);
        if (!checkNumber.equals("+994") || !operatorBeginningsOfAzerbaijan.contains(checkOperatorNumber)) {
            throw new WrongPhoneNumberException(ErrorMessage.NUMBER_IS_WRONG, HttpStatus.BAD_REQUEST.name());
        }

        List<String> allEmails = userRepository.findAll()
                .stream().map(User::getEmail)
                .collect(Collectors.toList());
        if (allEmails.contains(createRequest.getEmail())){
            throw new EmailAlreadyExistsException(ErrorMessage.EMAIL_ALREADY_EXISTS, HttpStatus.BAD_REQUEST.name());
        }
        User user = userMapper.createRequestToEntity(createRequest);
        user.setRoleType(RoleType.USER);
        userRepository.save(user);
        return userMapper.entityToCreateResponse(user);

    }

    @Override
    public UserReadResponse getId(Long id) {

        User foundedUser = userRepository.findById(id)
                .orElseThrow(() -> new UserNotFoundException(ErrorMessage.USER_NOT_FOUND, HttpStatus.NOT_FOUND.name()));
        return userMapper.entityToReadResponse(foundedUser);

    }

    @Override
    public List<UserReadResponse> getAll() {

        List<User> userList = userRepository.findAll();

        return userList.stream()
                .map(userMapper::entityToReadResponse)
                .toList();

    }

    @Override
    public UserUpdateResponse update(Long id, UserUpdateRequest updateRequest) {

        User entity = userRepository
                .findById(id).orElseThrow(() -> new UserNotFoundException(ErrorMessage.USER_NOT_FOUND, HttpStatus.NOT_FOUND.name()));
        User savedUser = userMapper.updateRequestToEntity(entity,updateRequest);
        userRepository.save(savedUser);

        return userMapper.entityToUpdateResponse(savedUser);

    }

    @Override
    public void delete(Long id) {
        User user = userRepository.findById(id)
                .orElseThrow(() -> new UserNotFoundException(ErrorMessage.USER_NOT_FOUND, HttpStatus.NOT_FOUND.name()));
        userRepository.deleteById(user.getId());
    }

    @Override
    public String loginUser(UserLoginRequest request) {

        User foundUser = userRepository.findByUsernameAndPassword(request.getUsername(), request.getPassword())
                .orElseThrow(() -> new UserNotFoundException(ErrorMessage.USER_NOT_FOUND, HttpStatus.NOT_FOUND.name()));

        if (foundUser != null) {
            return "login successful";
        }
        return "login failed";

        //todo:void qaytaracaq log.info atacam...
        //todo:encryption
    }

}
