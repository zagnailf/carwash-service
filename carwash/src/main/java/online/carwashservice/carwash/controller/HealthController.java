package online.carwashservice.carwash.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Mono;

@RestController
@Tag(name = "Тестовый контроллер", description = "Проверочный контроллер для реактивного контроллера")
public class HealthController {

    @Operation(
            summary = "Проверка доступности приложения",
            description = "При запросе возвращает сообщение \"ok\"")
    @ResponseStatus(HttpStatus.OK)
    @GetMapping(value = "/health", produces = MediaType.APPLICATION_NDJSON_VALUE)
    public Mono<String> health() {
        return Mono.justOrEmpty("ok");
    }
}
