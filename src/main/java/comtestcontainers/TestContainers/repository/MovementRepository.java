package comtestcontainers.TestContainers.repository;

import comtestcontainers.TestContainers.model.Movement;
import comtestcontainers.TestContainers.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface MovementRepository extends JpaRepository<Movement, Long> {

    List<Movement> findByUser(User user);

}

