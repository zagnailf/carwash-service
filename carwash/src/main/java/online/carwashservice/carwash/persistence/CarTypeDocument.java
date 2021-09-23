package online.carwashservice.carwash.persistence;

import lombok.Builder;
import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Data
@Builder
@Document(collection = "cartypes")
public class CarTypeDocument {
    @Id
    private String id;
    private String name;
    private String description;
}
