package comtestcontainers.TestContainers.model;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.math.BigDecimal;
import java.util.UUID;


@Getter
@Setter
@NoArgsConstructor
@Document("movements")
public class Movement {

    @Id
    private String id;

    private User user;

    private BigDecimal value;


    public Movement(User user, BigDecimal value) {
        this.id = UUID.randomUUID().toString();
        this.user = user;
        this.value = value;
    }

}
