package az.hamburg.autoservice.util;

import az.hamburg.autoservice.domain.Mechanic;
import az.hamburg.autoservice.model.mechanic.request.MechanicCreateRequest;
import az.hamburg.autoservice.model.mechanic.request.MechanicUpdateRequest;
import az.hamburg.autoservice.model.mechanic.response.MechanicCreateResponse;
import az.hamburg.autoservice.model.mechanic.response.MechanicReadResponse;
import az.hamburg.autoservice.model.mechanic.response.MechanicUpdateResponse;

import java.time.LocalDateTime;

public class MechanicUtil {

    private MechanicUtil() {
    }

    public static MechanicCreateRequest createRequest() {
        MechanicCreateRequest request = new MechanicCreateRequest();
        request.setName("John Doe");
        request.setEmail("john.doe@example.com");
        request.setSpecialty("Engine Repair");
        return request;
    }

    public static MechanicUpdateRequest updateRequest() {
        MechanicUpdateRequest request = new MechanicUpdateRequest();
        request.setName("Jane Doe");
        request.setEmail("jane.doe@example.com");
        request.setSpecialty("Transmission Repair");
        return request;
    }

    public static Mechanic mechanic() {
        Mechanic mechanic = new Mechanic();
        mechanic.setId(1L);
        mechanic.setName("John Doe");
        mechanic.setEmail("john.doe@example.com");
        mechanic.setSpecialty("Engine Repair");
        return mechanic;
    }

    public static Mechanic updatedMechanic() {
        Mechanic mechanic = new Mechanic();
        mechanic.setId(1L);
        mechanic.setName("Jane Doe");
        mechanic.setEmail("jane.doe@example.com");
        mechanic.setSpecialty("Transmission Repair");
        return mechanic;
    }

    public static MechanicCreateResponse mechanicCreateResponse() {
        MechanicCreateResponse response = new MechanicCreateResponse();
        response.setId(1L);
        response.setName("Test Mechanic");
        response.setEmail("mechanic@example.com");
        response.setSpecialty("Engine Specialist");
        response.setCreated(LocalDateTime.now());
        return response;
    }

    public static MechanicUpdateResponse mechanicUpdateResponse() {
        MechanicUpdateResponse response = new MechanicUpdateResponse();
        response.setId(1L);
        response.setName("Updated Mechanic");
        response.setEmail("updated_mechanic@example.com");
        response.setSpecialty("Transmission Specialist");
        response.setModified(LocalDateTime.now());
        return response;
    }

    public static MechanicReadResponse mechanicReadResponse() {
        MechanicReadResponse response = new MechanicReadResponse();
        response.setId(1L);
        response.setName("Mechanic Name");
        response.setEmail("mechanic@example.com");
        response.setSpecialty("Engine Specialist");
        response.setCreated(LocalDateTime.now().minusDays(5));
        response.setModified(LocalDateTime.now());
        return response;
    }
}
