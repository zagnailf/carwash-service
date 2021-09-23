package online.carwashservice.carwash.persistence;

import lombok.Builder;
import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Data
@Builder
@Document(collection = "positions")
public class PositionDocument {
    @Id
    private String id;
    private String name;
    private String parent;
}
