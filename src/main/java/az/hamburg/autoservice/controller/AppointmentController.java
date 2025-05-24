package az.hamburg.autoservice.controller;


import az.hamburg.autoservice.domain.Appointment;
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

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void delete(@PathVariable Long id ) {
        appointmentService.delete(id);
    }

//    @PutMapping("/{id}/status-update/{changerId}")
//    @ResponseStatus(HttpStatus.OK)
//    public AppointmentStatusUpdateResponse updateStatus(@PathVariable Long id, @PathVariable Long changerId, @RequestParam boolean status) {
//        return appointmentService.statusUpdate(id, changerId, status);
//    }

    @GetMapping("/status-pending")
    @ResponseStatus(HttpStatus.OK)
    public List<AppointmentReadResponse> getPendingStatus() {
        return appointmentService.getAllStatusPending();
    }

}
