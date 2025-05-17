package az.hamburg.autoservice.service;

import az.hamburg.autoservice.model.vehicle.request.VehicleCreateRequest;
import az.hamburg.autoservice.model.vehicle.request.VehicleUpdateRequest;
import az.hamburg.autoservice.model.vehicle.response.VehicleCreateResponse;
import az.hamburg.autoservice.model.vehicle.response.VehicleReadResponse;
import az.hamburg.autoservice.model.vehicle.response.VehicleUpdateResponse;

import java.util.List;

public interface VehicleService {

    VehicleCreateResponse create(VehicleCreateRequest createRequest);

    VehicleUpdateResponse update(Long id, VehicleUpdateRequest updateRequest);

    VehicleReadResponse getById(Long id);

    List<VehicleReadResponse> getAll();

    void  delete(Long id);
}
