package az.hamburg.autoservice.model.vehicle.request;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor

public class VehicleUpdateRequest {

    private String brand;
    private String model;
    private String plateNumber;

}
