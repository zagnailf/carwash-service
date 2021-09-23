package online.carwashservice.carwash.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import online.carwashservice.carwash.dto.request.CreateCarWashServiceClientRq;
import online.carwashservice.carwash.dto.result.CreateCarWashServiceClientResult;
import online.carwashservice.carwash.service.CarWashServiceClientService;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Mono;

@RestController
@RequestMapping("/api/v1/")
@Tag(name = "Клиент сервиса", description = "Операции с клиентом (владельцы автомоек) использующий сервис")
public class CarWashServiceClientV1Controller {

    private final CarWashServiceClientService carWashServiceClientService;

    public CarWashServiceClientV1Controller(CarWashServiceClientService carWashServiceClientService) {
        this.carWashServiceClientService = carWashServiceClientService;
    }

    @Operation(summary = "Создание клиента сервиса управления автомойками")
    @PostMapping(value = "/", consumes = MediaType.APPLICATION_NDJSON_VALUE)
    public Mono<CreateCarWashServiceClientResult> create(@RequestBody CreateCarWashServiceClientRq request) {
        return Mono.justOrEmpty(carWashServiceClientService.create(request));
    }
}
