package az.hamburg.autoservice.controller;

import az.hamburg.autoservice.model.appointmentmechanic.request.AppointmentMechanicCreateRequest;
import az.hamburg.autoservice.model.appointmentmechanic.request.AppointmentMechanicUpdateRequest;
import az.hamburg.autoservice.model.appointmentmechanic.response.AppointmentMechanicCreateResponse;
import az.hamburg.autoservice.model.appointmentmechanic.response.AppointmentMechanicReadResponse;
import az.hamburg.autoservice.model.appointmentmechanic.response.AppointmentMechanicUpdateResponse;
import az.hamburg.autoservice.service.AppointmentMechanicService;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("v1/appointment-mechanic")
@RequiredArgsConstructor
@Tag(name = "AppointmentMechanic Controller API", description = "Managing AppointmentMechanic Apis")
@CrossOrigin(allowedHeaders = "*", origins = "*")
public class AppointmentMechanicController {

    private final AppointmentMechanicService appointmentMechanicService;

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public AppointmentMechanicCreateResponse create(@RequestBody AppointmentMechanicCreateRequest createRequest) {
        return appointmentMechanicService.create(createRequest);

    }

    @GetMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public AppointmentMechanicReadResponse getId(@PathVariable Long id) {
        return appointmentMechanicService.getById(id);

    }

    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    public List<AppointmentMechanicReadResponse> getAll() {
        return appointmentMechanicService.getAll();

    }

    @PutMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public AppointmentMechanicUpdateResponse update(@PathVariable Long id, @RequestBody AppointmentMechanicUpdateRequest updateRequest) {
        return appointmentMechanicService.update(id, updateRequest);

    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void delete(@PathVariable Long id ) {
        appointmentMechanicService.delete(id);

    }
}

