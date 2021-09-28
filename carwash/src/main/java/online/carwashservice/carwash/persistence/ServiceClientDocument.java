package online.carwashservice.carwash.persistence;

import lombok.Builder;
import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

import java.util.List;

@Data
@Builder
@Document(collection = "service_clients")
public class ServiceClientDocument {
    @Id
    private String id;
    private String name;
    private String description;
    @Field(value = "telegram_bot_enabled")
    private boolean telegramBotEnabled;
    private boolean enabled;
    private List<AddressDocument> addresses;
    private List<PositionDocument> positions;
    @Field(value = "car_types")
    private List<CarTypeDocument> carTypes;
    @Field(value = "car_washes")
    private List<CarWashDocument> carWashes;
}
