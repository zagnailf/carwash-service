package online.carwashservice.carwash.dto.request;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
@AllArgsConstructor
@Schema(description = "Сущность клиента сервиса")
public class CreateCarWashServiceClientRq {
    @Schema(description = "Название автомойки")
    private String name;

    @Schema(description = "Описание автомойки")
    private String description;

    @Schema(description = "Активация телеграм бота для текущего клиента")
    private Boolean telegramBotEnabled;
}
