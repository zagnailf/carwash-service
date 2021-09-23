package online.carwashservice.carwash.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Mono;

@RestController
public class HealthController {

    @ResponseStatus(HttpStatus.OK)
    @GetMapping(value = "/health", produces = MediaType.APPLICATION_NDJSON_VALUE)
    public Mono<String> health() {
        return Mono.justOrEmpty("ok");
    }
}
