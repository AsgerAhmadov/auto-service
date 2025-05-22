package az.hamburg.autoservice.model.vehicle.request;

import az.hamburg.autoservice.validation.vehicle.VehicleBrand;
import az.hamburg.autoservice.validation.vehicle.VehicleModel;
import az.hamburg.autoservice.validation.vehicle.VehiclePlateNumber;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class VehicleCreateRequest {

    @VehicleBrand
    private String brand;

    @VehicleModel
    private String model;

    @VehiclePlateNumber
    private String plateNumber;
}
