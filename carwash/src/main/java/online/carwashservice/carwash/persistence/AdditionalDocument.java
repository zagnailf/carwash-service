package online.carwashservice.carwash.persistence;

import lombok.Builder;
import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.math.BigDecimal;

@Data
@Builder
@Document(collection = "additions")
public class AdditionalDocument {
    @Id
    private String id;
    private String name;
    private String description;
    private BigDecimal cost;
}
