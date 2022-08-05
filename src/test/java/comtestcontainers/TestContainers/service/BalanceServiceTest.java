package comtestcontainers.TestContainers.service;

import comtestcontainers.TestContainers.TestContainersApplication;
import comtestcontainers.TestContainers.containers.MySQLTestContainer;

import comtestcontainers.TestContainers.model.Movement;
import comtestcontainers.TestContainers.model.User;
import comtestcontainers.TestContainers.repository.MovementRepository;
import comtestcontainers.TestContainers.repository.UserRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.testcontainers.containers.MySQLContainer;
import org.testcontainers.junit.jupiter.Container;
import org.testcontainers.junit.jupiter.Testcontainers;

import java.math.BigDecimal;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

@Testcontainers
@ActiveProfiles("test")
@ExtendWith(SpringExtension.class)
@SpringBootTest(classes = TestContainersApplication.class)
class BalanceServiceTest {

    @Container
    public static MySQLContainer container = MySQLTestContainer.getInstance();

    @Autowired
    private MovementRepository movementRepository;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private BalanceService balanceService;

    @Test
    void testTrue(){

        final var user = userRepository.save(new User("Gabriel"));

        movementRepository.save(new Movement(user, new BigDecimal("5.00")));
        movementRepository.save(new Movement(user, new BigDecimal("15.00")));
        movementRepository.save(new Movement(user, new BigDecimal("-10.00")));

        assertEquals(new BigDecimal("10.00"), balanceService.getBalanceBy(user));

    }

}