package online.carwashservice.carwash.persistence;

import lombok.Builder;
import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

import java.math.BigDecimal;

@Data
@Builder
@Document(collection = "services")
public class ServiceDocument {
    @Id
    private String id;
    private String name;
    private String description;
    @DBRef
    @Field(value = "car_type")
    private CarTypeDocument carType;
    private BigDecimal cost;
}
