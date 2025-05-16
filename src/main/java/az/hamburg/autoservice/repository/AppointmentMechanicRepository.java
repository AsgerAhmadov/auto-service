package az.hamburg.autoservice.repository;

import az.hamburg.autoservice.domain.AppointmentMechanic;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AppointmentMechanicRepository extends JpaRepository<AppointmentMechanic, Long> {

}
