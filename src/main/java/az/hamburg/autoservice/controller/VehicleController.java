package az.hamburg.autoservice.controller;

import az.hamburg.autoservice.model.vehicle.request.VehicleCreateRequest;
import az.hamburg.autoservice.model.vehicle.request.VehicleUpdateRequest;
import az.hamburg.autoservice.model.vehicle.response.VehicleCreateResponse;
import az.hamburg.autoservice.model.vehicle.response.VehicleReadResponse;
import az.hamburg.autoservice.model.vehicle.response.VehicleUpdateResponse;
import az.hamburg.autoservice.service.VehicleService;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("v1/vehicles")
@RequiredArgsConstructor
@Tag(name = "Vehicle Controller API", description = "Managing Vehicle Apis")
@CrossOrigin(allowedHeaders = "*", origins = "*")
public class VehicleController {

    private final VehicleService vehicleService;

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public VehicleCreateResponse create( @RequestBody VehicleCreateRequest createRequest) {
        return vehicleService.create(createRequest);
    }

    @GetMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public VehicleReadResponse getId(@PathVariable Long id) {
        return vehicleService.getById(id);
    }

    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    public List<VehicleReadResponse> getAll() {
        return vehicleService.getAll();
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void delete(@PathVariable Long id ) {
        vehicleService.delete(id);
    }



    @PutMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public VehicleUpdateResponse update(@PathVariable Long id,@RequestBody VehicleUpdateRequest updateRequest) {
        return vehicleService.update(id, updateRequest);
    }
}
