package az.hamburg.autoservice.controller;


import az.hamburg.autoservice.domain.RequestStatus;
import az.hamburg.autoservice.model.appointment.request.AppointmentCreateRequest;
import az.hamburg.autoservice.model.appointment.request.AppointmentUpdateRequest;
import az.hamburg.autoservice.model.appointment.response.AppointmentCreateResponse;
import az.hamburg.autoservice.model.appointment.response.AppointmentReadResponse;
import az.hamburg.autoservice.model.appointment.response.AppointmentStatusUpdateResponse;
import az.hamburg.autoservice.model.appointment.response.AppointmentUpdateResponse;
import az.hamburg.autoservice.service.AppointmentService;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static org.springframework.data.jpa.domain.AbstractPersistable_.id;

@RestController
@RequestMapping("api/appointments")
@RequiredArgsConstructor
@Tag(name = "Appointment Controller API", description = "Managing Appointment Apis")
@CrossOrigin(allowedHeaders = "*", origins = "*")
public class AppointmentController {
    
    private final AppointmentService appointmentService;

    @PostMapping("/{vehicleId}")
    @ResponseStatus(HttpStatus.CREATED)
    public AppointmentCreateResponse create(@PathVariable Long vehicleId,@RequestBody AppointmentCreateRequest createRequest) {
        return appointmentService.create(vehicleId,createRequest);
    }

    @GetMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public AppointmentReadResponse getId(@PathVariable Long id) {
        return appointmentService.getById(id);
    }

    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    public List<AppointmentReadResponse> getAll() {
        return appointmentService.getAll();
    }

    @PutMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public AppointmentUpdateResponse update(@PathVariable Long id, @RequestBody AppointmentUpdateRequest updateRequest) {
        return appointmentService.update(id, updateRequest);
    }

    @DeleteMapping("{userId}/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void delete(@PathVariable Long userId ,@PathVariable Long id ) {
        appointmentService.delete(userId,id);
    }

    @PutMapping("/{userId}/status-update/{appointmentId}")
    @ResponseStatus(HttpStatus.OK)
    public AppointmentStatusUpdateResponse updateStatus(@PathVariable Long userId,@PathVariable Long appointmentId, @RequestParam RequestStatus status) {
        return appointmentService.statusUpdate(userId, appointmentId, status);
    }

    @GetMapping("/status-pending")
    @ResponseStatus(HttpStatus.OK)
    public List<AppointmentReadResponse> getPendingStatus() {
        return appointmentService.getAllStatusPending();
    }

    @DeleteMapping("delete-completed/{appointmentId}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteCompleted(@PathVariable Long appointmentId) {
        appointmentService.deleteCompleted(appointmentId);
    }

}
