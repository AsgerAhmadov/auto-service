package az.hamburg.autoservice.controller;

import az.hamburg.autoservice.domain.RoleType;
import az.hamburg.autoservice.model.user.request.UserChangePassword;
import az.hamburg.autoservice.model.user.request.UserCreateRequest;
import az.hamburg.autoservice.model.user.request.UserLoginRequest;
import az.hamburg.autoservice.model.user.request.UserUpdateRequest;
import az.hamburg.autoservice.model.user.response.*;
import az.hamburg.autoservice.service.UserService;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/users")
@RequiredArgsConstructor
@Tag(name = "User Controller API", description = "Managing User Apis")
@CrossOrigin(allowedHeaders = "*", origins = "*")
public class UserController {

    private final UserService userService;

    @PostMapping("/register")
    @ResponseStatus(HttpStatus.CREATED)
    public UserCreateResponse create(@Valid @RequestBody UserCreateRequest createRequest) {
        return userService.create(createRequest);
    }

    @GetMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public UserReadResponse getId(@PathVariable Long id) {
        return userService.getId(id);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteById(@PathVariable Long id) {
        userService.delete(id);
    }

    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    public List<UserReadResponse> getAll() {
        return userService.getAll();
    }

    @PutMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public UserUpdateResponse update(@PathVariable Long id, @RequestBody UserUpdateRequest updateRequest) {
        return userService.update(id, updateRequest);
    }

    @PostMapping("/login")
    @ResponseStatus(HttpStatus.OK)
    public String login(@RequestBody UserLoginRequest loginRequest) {
        return userService.loginUser(loginRequest);
    }

    @PutMapping("/{changerId}/role-update/{id}")
    @ResponseStatus(HttpStatus.OK)
    public UserRoleUpdateResponse updateRole( @PathVariable Long changerId,@PathVariable Long id, @RequestParam RoleType roleType) {
        return userService.roleUpdate(changerId, id, roleType);
    }

    @PostMapping("/{userId}/change-password")
    @ResponseStatus(HttpStatus.OK)
    public String changePassword(@PathVariable Long userId, @RequestBody UserChangePassword changePassword) {
        return userService.changePassword(userId, changePassword);
    }

}
