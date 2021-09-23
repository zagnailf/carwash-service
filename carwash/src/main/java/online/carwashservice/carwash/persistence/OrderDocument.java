package online.carwashservice.carwash.persistence;

import lombok.Builder;
import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

import java.math.BigDecimal;
import java.time.Instant;
import java.util.List;

@Data
@Builder
@Document(collection = "orders")
public class OrderDocument {
    @Id
    private String id;
    private String description;
    @DBRef
    private List<ServiceDocument> services;
    @DBRef
    private List<AdditionalDocument> additions;
    @DBRef
    private EmployeeDocument washerman;
    @DBRef
    private EmployeeDocument admin;
    private BigDecimal cost;
    @Field(value = "created_at")
    private Instant createdAt;
    @Field(value = "updated_at")
    private Instant updatedAt;
}
