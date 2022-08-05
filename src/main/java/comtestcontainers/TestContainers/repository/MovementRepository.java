package comtestcontainers.TestContainers.repository;

import comtestcontainers.TestContainers.model.Movement;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.List;

public interface MovementRepository extends MongoRepository<Movement, String> {

    List<Movement> findByUserId(Integer id);


}
