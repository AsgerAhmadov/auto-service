package az.hamburg.autoservice.repository;

import az.hamburg.autoservice.domain.Vehicle;
import org.springframework.data.jpa.repository.JpaRepository;

public interface VehicleRepository extends JpaRepository<Vehicle, Long> {

}
