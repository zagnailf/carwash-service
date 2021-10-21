package online.carwashservice.carwash.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.extern.slf4j.Slf4j;
import online.carwashservice.carwash.dto.request.CarWashServiceClientRq;
import online.carwashservice.carwash.dto.request.EnableServiceRq;
import online.carwashservice.carwash.persistence.ServiceClientDocument;
import online.carwashservice.carwash.service.CarWashServiceClientService;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Slf4j
@RestController
@RequestMapping("/api/v1/clients")
@Tag(name = "Клиент сервиса", description = "Операции с клиентом (владельцы автомоек) использующий сервис")
public class CarWashServiceClientV1Controller {

    private final CarWashServiceClientService carWashServiceClientService;

    public CarWashServiceClientV1Controller(CarWashServiceClientService carWashServiceClientService) {
        this.carWashServiceClientService = carWashServiceClientService;
    }

    @Operation(summary = "Создание клиента сервиса управления автомойками")
    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping(
            value = "/",
            //headers = {"Accept=application/vnd.company.app-1.0+json"},
            produces = MediaType.APPLICATION_NDJSON_VALUE,
            consumes = MediaType.APPLICATION_NDJSON_VALUE)
    public Mono<ServiceClientDocument> create(@RequestBody CarWashServiceClientRq request) {
        log.info("Create client with name: '{}'", request.getName());
        return carWashServiceClientService.create(request);
    }

    @Operation(summary = "Получить всех клиентов сервиса")
    @GetMapping(value = "/", produces = {MediaType.APPLICATION_NDJSON_VALUE})
    public Flux<ServiceClientDocument> getAllClientServices() {
        Flux<ServiceClientDocument> allClientServices = carWashServiceClientService.getAllClientServices();
        allClientServices.count().subscribe(count -> log.info("Get all clients count: '{}'", count));
        return allClientServices;
    }

    @Operation(summary = "Получить всех активных клиентов сервиса")
    @GetMapping(value = "/active", produces = MediaType.APPLICATION_NDJSON_VALUE)
    public Flux<ServiceClientDocument> getAllEnabledClientServices() {
        Flux<ServiceClientDocument> allEnabledClientServices = carWashServiceClientService.getAllEnabledClientServices();
        allEnabledClientServices.count().subscribe(count -> log.info("Get all enabled clients count '{}'", count));
        return allEnabledClientServices;
    }

    @Operation(summary = "Получить клиента по UUID")
    @GetMapping(value = "/{uuid}", produces = MediaType.APPLICATION_NDJSON_VALUE)
    public Mono<ServiceClientDocument> getClient(@PathVariable("uuid") String uuid) {
        Mono<ServiceClientDocument> mono = carWashServiceClientService.getClientByUUID(uuid);
        mono.subscribe(client -> log.info("Get client '{}'", client));
        return mono;
    }

    @Operation(summary = "Включить/выключить клиента")
    @PutMapping(value = "/{uuid}/enable", produces = MediaType.APPLICATION_NDJSON_VALUE)
    public Mono<ServiceClientDocument> enableClient(@PathVariable("uuid") String uuid,
                                                    @RequestBody EnableServiceRq enableServiceRq) {
        log.info("Update client with id '{}' enable field to '{}'", uuid, enableServiceRq.isEnable());
        return carWashServiceClientService.enableClient(uuid, enableServiceRq.isEnable());
    }

    @Operation(summary = "Включить/выключить телеграм бот клиента")
    @PutMapping(value = "/{uuid}/telegram-bot", produces = MediaType.APPLICATION_NDJSON_VALUE)
    public Mono<ServiceClientDocument> enableTelegramBot(@PathVariable("uuid") String uuid,
                                                    @RequestBody EnableServiceRq enableServiceRq) {
        log.info("Update client with id '{}' telegramBotEnabled field to '{}'", uuid, enableServiceRq.isEnable());
        return carWashServiceClientService.enableTelegramBot(uuid, enableServiceRq.isEnable());
    }

    @Operation(summary = "Обновить информацию о клиенте по UUID")
    @PutMapping(value = "/{uuid}", produces = MediaType.APPLICATION_NDJSON_VALUE)
    public Mono<ServiceClientDocument> updateClient(@PathVariable("uuid") String uuid,
                                                    @RequestBody CarWashServiceClientRq request) {
        log.info("Update client with id '{}' fields to {}", uuid, request.toString());
        return carWashServiceClientService.update(uuid, request);
    }
}
