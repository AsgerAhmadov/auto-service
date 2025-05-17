package az.hamburg.autoservice.controller;

import az.hamburg.autoservice.model.mechanic.request.MechanicCreateRequest;
import az.hamburg.autoservice.model.mechanic.request.MechanicUpdateRequest;
import az.hamburg.autoservice.model.mechanic.response.MechanicCreateResponse;
import az.hamburg.autoservice.model.mechanic.response.MechanicReadResponse;
import az.hamburg.autoservice.model.mechanic.response.MechanicUpdateResponse;
import az.hamburg.autoservice.service.MechanicService;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/mechanics")
@RequiredArgsConstructor
@Tag(name = "Mechanic Controller API", description = "Managing Mechanic Apis")
@CrossOrigin(allowedHeaders = "*", origins = "*")
public class MechanicController {

    private final MechanicService mechanicService;

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public MechanicCreateResponse create(@Valid @RequestBody MechanicCreateRequest createRequest) {
        return mechanicService.create(createRequest);
    }

    @GetMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public MechanicReadResponse getId(@PathVariable Long id) {
        return mechanicService.getId(id);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteById(@PathVariable Long id) {
        mechanicService.delete(id);
    }

    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    public List<MechanicReadResponse> getAll() {
        return mechanicService.getAll();
    }

    @PutMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public MechanicUpdateResponse update(@PathVariable Long id, @RequestBody MechanicUpdateRequest updateRequest) {
        return mechanicService.update(id, updateRequest);
    }

}
