package az.hamburg.autoservice.repository;

import az.hamburg.autoservice.domain.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Long> {
}
