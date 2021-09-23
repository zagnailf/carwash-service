package online.carwashservice.carwash.dto.result;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import online.carwashservice.carwash.persistence.ServiceClientDocument;

@Data
@Builder
@AllArgsConstructor
@Schema(description = "Результат создания клиента сервиса")
public class CreateCarWashServiceClientResult {
    @Schema(description = "Сущность клиента сервиса")
    private ServiceClientDocument result;
}
