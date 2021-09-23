package online.carwashservice.carwash.persistence;

import lombok.Builder;
import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.Date;

@Data
@Builder
@Document(collection = "employees")
public class EmployeeDocument {
    @Id
    private String id;
    private String lastName;
    private String firstName;
    private String middleName;
    private Date birthday;
    @DBRef
    private PositionDocument position;
}
