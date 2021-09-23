package online.carwashservice.carwash.persistence;

import lombok.Builder;
import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Data
@Builder
@Document(collection = "addresses")
public class AddressDocument {
    @Id
    private String id;
    private String country;
    private String street;
    private String city;
    private String house;
}
