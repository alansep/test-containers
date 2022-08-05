package comtestcontainers.TestContainers.service;

import comtestcontainers.TestContainers.containers.MySQLTestContainer;
import comtestcontainers.TestContainers.model.Movement;
import comtestcontainers.TestContainers.model.User;
import comtestcontainers.TestContainers.repository.MovementRepository;
import comtestcontainers.TestContainers.repository.UserRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.testcontainers.containers.MySQLContainer;
import org.testcontainers.junit.jupiter.Container;
import org.testcontainers.junit.jupiter.Testcontainers;

import java.math.BigDecimal;

import static org.junit.jupiter.api.Assertions.assertEquals;

@Testcontainers
@SpringBootTest
class BalanceServiceTest {

    @Container
    public static MySQLContainer container = MySQLTestContainer.getInstance();

    @Autowired
    private MovementRepository movementRepository;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private BalanceService balanceService;

    private User user;

    @BeforeEach
    void setUp(){

        movementRepository.deleteAll();
        userRepository.deleteAll();

        user = userRepository.save(new User("Gabriel"));

    }

    @Test
    void givenAnUserAndThreeMovements_whenCallGetBalanceBy_ReturnsValueEqualsToTen() {

        movementRepository.save(new Movement(user, new BigDecimal("5.00")));
        movementRepository.save(new Movement(user, new BigDecimal("15.00")));
        movementRepository.save(new Movement(user, new BigDecimal("-10.00")));

        assertEquals(new BigDecimal("10.00"), balanceService.getBalanceBy(user));

    }

    @Test
    void givenAnUserAndThreeMovements_whenCallGetBalanceBy_ReturnsValueEqualsToZero() {

        movementRepository.save(new Movement(user, new BigDecimal("5.00")));
        movementRepository.save(new Movement(user, new BigDecimal("15.00")));
        movementRepository.save(new Movement(user, new BigDecimal("-20.00")));

        assertEquals(new BigDecimal("00.00"), balanceService.getBalanceBy(user));

    }

}