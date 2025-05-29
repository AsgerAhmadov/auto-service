package az.hamburg.autoservice.model.vehicle.response;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class VehicleReadResponse {

    private Long id;

    private Long userId;
    private String brand;
    private String model;
    private String plateNumber;
    private LocalDateTime created;
    private LocalDateTime modified;
}
