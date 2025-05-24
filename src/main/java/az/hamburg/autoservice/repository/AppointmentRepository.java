package az.hamburg.autoservice.repository;

import az.hamburg.autoservice.domain.Appointment;
import az.hamburg.autoservice.domain.RequestStatus;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

public interface AppointmentRepository extends JpaRepository<Appointment, Long> {

    List<Appointment> getAllAppointmentByStatus(RequestStatus status);
}
