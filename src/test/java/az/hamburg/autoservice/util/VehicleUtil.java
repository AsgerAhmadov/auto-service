package az.hamburg.autoservice.util;

import az.hamburg.autoservice.domain.User;
import az.hamburg.autoservice.domain.Vehicle;
import az.hamburg.autoservice.model.vehicle.request.VehicleCreateRequest;
import az.hamburg.autoservice.model.vehicle.request.VehicleUpdateRequest;
import az.hamburg.autoservice.model.vehicle.response.VehicleCreateResponse;
import az.hamburg.autoservice.model.vehicle.response.VehicleReadResponse;
import az.hamburg.autoservice.model.vehicle.response.VehicleUpdateResponse;

import java.time.LocalDateTime;

public class VehicleUtil {

    private VehicleUtil(){

    }

    public static VehicleCreateRequest createRequest() {
        VehicleCreateRequest request = new VehicleCreateRequest();
        request.setBrand("Toyota");
        request.setModel("Corolla");
        request.setPlateNumber("99-AB-123");
        return request;
    }

    public static VehicleUpdateRequest updateRequest() {
        VehicleUpdateRequest request = new VehicleUpdateRequest();
        request.setBrand("BMW");
        request.setModel("X5");
        request.setPlateNumber("10-XY-456");
        return request;
    }

    public static Vehicle vehicle() {
        Vehicle vehicle = new Vehicle();
        vehicle.setId(1L);
        vehicle.setUserId(1L);
        vehicle.setBrand("Toyota");
        vehicle.setModel("Corolla");
        vehicle.setPlateNumber("99-AB-123");
        return vehicle;
    }

    public static User user() {
        User user = new User();
        user.setId(1L);
        user.setName("Test User");
        user.setEmail("test@example.com");
        return user;
    }

    public static VehicleCreateResponse vehicleCreateResponse() {
        VehicleCreateResponse response = new VehicleCreateResponse();
        response.setId(1L);
        response.setUserId(1L);
        response.setBrand("Toyota");
        response.setModel("Corolla");
        response.setPlateNumber("99-AA-999");
        response.setCreated(LocalDateTime.now());
        return response;
    }

    public static VehicleUpdateResponse vehicleUpdateResponse() {
        VehicleUpdateResponse response = new VehicleUpdateResponse();
        response.setId(1L);
        response.setUserId(1L);
        response.setBrand("Honda");
        response.setModel("Civic");
        response.setPlateNumber("99-BB-123");
        response.setModified(LocalDateTime.now());
        return response;
    }

    public static VehicleReadResponse vehicleReadResponse() {
        VehicleReadResponse response = new VehicleReadResponse();
        response.setId(1L);
        response.setUserId(1L);
        response.setBrand("Toyota");
        response.setModel("Corolla");
        response.setPlateNumber("99-AA-999");
        response.setCreated(LocalDateTime.now().minusDays(1));
        response.setModified(LocalDateTime.now());
        return response;
    }

}
