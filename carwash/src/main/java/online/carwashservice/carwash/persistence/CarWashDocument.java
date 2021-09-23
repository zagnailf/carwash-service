package online.carwashservice.carwash.persistence;

import lombok.Builder;
import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.List;

@Data
@Builder
@Document(collection = "carwashes")
public class CarWashDocument {
    @Id
    private String id;
    private String name;
    private String description;
    @DBRef
    private AddressDocument address;
    private List<ServiceDocument> services;
    private List<AdditionalDocument> additions;
    private List<EmployeeDocument> employees;
    private List<OrderDocument> orders;
}
