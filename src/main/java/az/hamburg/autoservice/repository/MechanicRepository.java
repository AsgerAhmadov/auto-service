package az.hamburg.autoservice.repository;

import az.hamburg.autoservice.domain.Mechanic;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MechanicRepository extends JpaRepository<Mechanic, Long> {
}
