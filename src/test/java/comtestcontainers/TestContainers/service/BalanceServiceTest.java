package comtestcontainers.TestContainers.service;

import comtestcontainers.TestContainers.containers.MongoDBTestContainer;
import comtestcontainers.TestContainers.containers.MySQLTestContainer;
import comtestcontainers.TestContainers.model.Movement;
import comtestcontainers.TestContainers.model.User;
import comtestcontainers.TestContainers.repository.MovementRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.testcontainers.containers.MongoDBContainer;
import org.testcontainers.containers.MySQLContainer;
import org.testcontainers.junit.jupiter.Container;
import org.testcontainers.junit.jupiter.Testcontainers;

import java.math.BigDecimal;

import static org.junit.jupiter.api.Assertions.assertEquals;

@Testcontainers
@SpringBootTest
class BalanceServiceTest {

    @Container
    public static MongoDBContainer container = MongoDBTestContainer.getInstance();

    @Autowired
    private MovementRepository movementRepository;

    @Autowired
    private BalanceService balanceService;

    private User user1, user2;

    @BeforeEach
    void setUp(){
        movementRepository.deleteAll();
        user1 = new User(1, "Vladimir");
        user2 = new User(2, "Ednaldo");
    }

    @Test
    void givenAnUserAndThreeMovements_whenCallGetBalanceBy_ReturnsValueEqualsToTen() {

        movementRepository.save(new Movement(user1, new BigDecimal("5.00")));
        movementRepository.save(new Movement(user2, new BigDecimal("15.00")));
        movementRepository.save(new Movement(user2, new BigDecimal("-10.00")));

        assertEquals(new BigDecimal("5.00"), balanceService.getBalanceBy(user2));

    }

    @Test
    void givenAnUserAndThreeMovements_whenCallGetBalanceBy_ReturnsValueEqualsToZero() {

        movementRepository.save(new Movement(user2, new BigDecimal("5.00")));
        movementRepository.save(new Movement(user1, new BigDecimal("15.00")));
        movementRepository.save(new Movement(user1, new BigDecimal("-20.00")));

        assertEquals(new BigDecimal("-5.00"), balanceService.getBalanceBy(user1));

    }

}