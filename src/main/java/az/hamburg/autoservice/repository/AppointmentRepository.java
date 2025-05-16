package az.hamburg.autoservice.repository;

import az.hamburg.autoservice.domain.Appointment;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AppointmentRepository extends JpaRepository<Appointment, Long> {

}
