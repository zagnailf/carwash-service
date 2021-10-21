package online.carwashservice.carwash.controller;

import online.carwashservice.carwash.dto.request.CarWashServiceClientRq;
import online.carwashservice.carwash.dto.request.EnableServiceRq;
import online.carwashservice.carwash.persistence.ServiceClientDocument;
import online.carwashservice.carwash.persistence.ServiceClientRepository;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.junit.jupiter.api.TestMethodOrder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.reactive.AutoConfigureWebTestClient;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.web.reactive.server.WebTestClient;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import reactor.test.StepVerifier;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

@SpringBootTest
@ActiveProfiles("test")
@AutoConfigureWebTestClient
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
class CarWashServiceClientV1ControllerTest {

    @Autowired
    private WebTestClient webTestClient;

    @Autowired
    private ServiceClientRepository repository;

    private ServiceClientDocument savedServiceClientDocument;
    private ServiceClientDocument secondServiceClientDocument;

    @Test
    @Order(0)
    void createClientShouldReturnOk() {
        Mono<CarWashServiceClientRq> request = Mono.just(CarWashServiceClientRq.builder()
                        .name("testName")
                        .description("testDescription")
                        .telegramBotEnabled(true)
                        .build());

        // When
        Flux<ServiceClientDocument> document = webTestClient.post()
                .uri("/api/v1/clients/")
                .contentType(MediaType.APPLICATION_NDJSON)
                .body(request, CarWashServiceClientRq.class)
                .exchange()
                .expectStatus().isCreated()
                .returnResult(ServiceClientDocument.class).getResponseBody()
                .log();

        document.next().subscribe(doc -> this.savedServiceClientDocument = doc);

        // Then
        assertNotNull(savedServiceClientDocument);
        assertNotNull(savedServiceClientDocument.getId());
        assertEquals("testName", savedServiceClientDocument.getName());
        assertEquals("testDescription", savedServiceClientDocument.getDescription());
        assertTrue(savedServiceClientDocument.isTelegramBotEnabled());
        assertFalse(savedServiceClientDocument.isEnabled());
        assertNull(savedServiceClientDocument.getAddresses());
        assertNull(savedServiceClientDocument.getCarWashes());
        assertNull(savedServiceClientDocument.getCarTypes());
        assertNull(savedServiceClientDocument.getPositions());
    }

    @Test
    @Order(1)
    void createSecondClientShould() {
        Mono<CarWashServiceClientRq> request = Mono.just(CarWashServiceClientRq.builder()
                        .name("testName 2")
                        .description("testDescription 2")
                        .telegramBotEnabled(true)
                        .build());

        // When
        Flux<ServiceClientDocument> document = webTestClient.post()
                .uri("/api/v1/clients/")
                .contentType(MediaType.APPLICATION_NDJSON)
                .body(request, CarWashServiceClientRq.class)
                .exchange()
                .expectStatus().isCreated()
                .returnResult(ServiceClientDocument.class).getResponseBody()
                .log();

        document.next().subscribe(doc -> this.secondServiceClientDocument = doc);
    }

    @Test
    @Order(2)
    void getAllClientServices() {
        Flux<ServiceClientDocument> documents = webTestClient.get()
                .uri("/api/v1/clients/")
                .exchange()
                .expectStatus().isOk()
                .returnResult(ServiceClientDocument.class).getResponseBody()
                .log();

        StepVerifier.create(documents)
                .expectSubscription()
                .expectNextCount(2)
                .verifyComplete();
    }

    @Test
    @Order(3)
    void activeSavedClientService() {
        Flux<ServiceClientDocument> document = webTestClient.put()
                .uri(String.format("/api/v1/clients/%s/enable", savedServiceClientDocument.getId()))
                .contentType(MediaType.APPLICATION_NDJSON)
                .body(Mono.just(new EnableServiceRq(true)), EnableServiceRq.class)
                .exchange()
                .expectStatus().isOk()
                .returnResult(ServiceClientDocument.class).getResponseBody()
                .log();

        document.next().subscribe(doc -> this.savedServiceClientDocument = doc);

        // Then
        assertNotNull(savedServiceClientDocument);
        assertNotNull(savedServiceClientDocument.getId());
        assertEquals("testName", savedServiceClientDocument.getName());
        assertEquals("testDescription", savedServiceClientDocument.getDescription());
        assertTrue(savedServiceClientDocument.isTelegramBotEnabled());
        assertTrue(savedServiceClientDocument.isEnabled());
        assertNull(savedServiceClientDocument.getAddresses());
        assertNull(savedServiceClientDocument.getCarWashes());
        assertNull(savedServiceClientDocument.getCarTypes());
        assertNull(savedServiceClientDocument.getPositions());
    }

    @Test
    @Order(4)
    void getAllActiveClientServices() {
        Flux<ServiceClientDocument> documents = webTestClient.get()
                .uri("/api/v1/clients/active")
                .exchange()
                .expectStatus().isOk()
                .returnResult(ServiceClientDocument.class).getResponseBody()
                .log();

        StepVerifier.create(documents)
                .expectSubscription()
                .expectNextCount(1)
                .verifyComplete();
    }

    @Test
    @Order(5)
    void getByUUID() {
        Flux<ServiceClientDocument> document = webTestClient.get()
                .uri(String.format("/api/v1/clients/%s", secondServiceClientDocument.getId()))
                .exchange()
                .expectStatus().isOk()
                .returnResult(ServiceClientDocument.class).getResponseBody()
                .log();

        document.next().subscribe(doc -> this.secondServiceClientDocument = doc);

        // Then
        assertNotNull(secondServiceClientDocument);
        assertNotNull(secondServiceClientDocument.getId());
        assertEquals("testName 2", secondServiceClientDocument.getName());
        assertEquals("testDescription 2", secondServiceClientDocument.getDescription());
        assertTrue(secondServiceClientDocument.isTelegramBotEnabled());
        assertFalse(secondServiceClientDocument.isEnabled());
        assertNull(secondServiceClientDocument.getAddresses());
        assertNull(secondServiceClientDocument.getCarWashes());
        assertNull(secondServiceClientDocument.getCarTypes());
        assertNull(secondServiceClientDocument.getPositions());
    }

    @Test
    @Order(6)
    void clientServiceDisableTelegramBot() {
        Flux<ServiceClientDocument> document = webTestClient.put()
                .uri(String.format("/api/v1/clients/%s/telegram-bot", secondServiceClientDocument.getId()))
                .contentType(MediaType.APPLICATION_NDJSON)
                .body(Mono.just(new EnableServiceRq(false)), EnableServiceRq.class)
                .exchange()
                .expectStatus().isOk()
                .returnResult(ServiceClientDocument.class).getResponseBody()
                .log();

        document.next().subscribe(doc -> this.secondServiceClientDocument = doc);

        // Then
        assertNotNull(secondServiceClientDocument);
        assertNotNull(secondServiceClientDocument.getId());
        assertEquals("testName 2", secondServiceClientDocument.getName());
        assertEquals("testDescription 2", secondServiceClientDocument.getDescription());
        assertFalse(secondServiceClientDocument.isTelegramBotEnabled());
        assertFalse(secondServiceClientDocument.isEnabled());
        assertNull(secondServiceClientDocument.getAddresses());
        assertNull(secondServiceClientDocument.getCarWashes());
        assertNull(secondServiceClientDocument.getCarTypes());
        assertNull(secondServiceClientDocument.getPositions());
    }

    @AfterAll
    void cleanCarWashServiceClientDocument() {
        repository.deleteAll().subscribe();
    }

}