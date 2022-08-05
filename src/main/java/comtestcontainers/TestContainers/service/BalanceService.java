package comtestcontainers.TestContainers.service;

import comtestcontainers.TestContainers.model.Movement;
import comtestcontainers.TestContainers.model.User;
import comtestcontainers.TestContainers.repository.MovementRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;

@Service
@RequiredArgsConstructor
public class BalanceService {
    private final MovementRepository movementRepository;

    public BigDecimal getBalanceBy(User user){
        return movementRepository.findByUser(user).stream().map(Movement::getValue).reduce(BigDecimal::add).orElse(BigDecimal.ZERO);
    }

}
