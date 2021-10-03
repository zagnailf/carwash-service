package online.carwashservice.carwash.dto.request;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.ToString;

@Data
@Builder
@ToString
@AllArgsConstructor
@Schema(description = "Сущность клиента сервиса")
public class CarWashServiceClientRq {
    @Schema(description = "Название автомойки")
    private String name;

    @Schema(description = "Описание автомойки")
    private String description;

    @Schema(description = "Активация телеграм бота для текущего клиента")
    private Boolean telegramBotEnabled;
}
