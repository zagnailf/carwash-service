package online.carwashservice.carwash.service;

import online.carwashservice.carwash.dto.request.CarWashServiceClientRq;
import online.carwashservice.carwash.persistence.ServiceClientDocument;
import online.carwashservice.carwash.persistence.ServiceClientRepository;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Service
public class CarWashServiceClientService {

    private final ServiceClientRepository serviceClientRepository;

    public CarWashServiceClientService(ServiceClientRepository serviceClientRepository) {
        this.serviceClientRepository = serviceClientRepository;
    }

    /**
     * Создание клиента сервиса. Доступ должен быть только у администратора/модератора сервиса
     * @return модель клиента
     */
    public Mono<ServiceClientDocument> create(CarWashServiceClientRq request) {
        ServiceClientDocument serviceClientDocument = ServiceClientDocument.builder()
                .name(request.getName())
                .description(request.getDescription())
                .telegramBotEnabled(request.getTelegramBotEnabled())
                .enabled(false)
                .build();
        return serviceClientRepository.save(serviceClientDocument);
    }

    public Flux<ServiceClientDocument> getAllClientServices() {
        return serviceClientRepository.findAll();
    }

    public Flux<ServiceClientDocument> getAllEnabledClientServices() {
        return serviceClientRepository.findByEnabled(true);
    }

    public Mono<ServiceClientDocument> getClientByUUID(String uuid) {
        return serviceClientRepository.findById(uuid);
    }

    public Mono<ServiceClientDocument> update(String uuid, CarWashServiceClientRq request) {
        Mono<ServiceClientDocument> mono = serviceClientRepository.findById(uuid);
        return mono.flatMap(client -> updateClient(client, request));
    }

    private Mono<? extends ServiceClientDocument> updateClient(ServiceClientDocument client, CarWashServiceClientRq request) {
        client.setName(request.getName());
        client.setDescription(request.getDescription());
        client.setTelegramBotEnabled(request.getTelegramBotEnabled());
        return serviceClientRepository.save(client);
    }

    public Mono<ServiceClientDocument> enableClient(String uuid, boolean enable) {
        Mono<ServiceClientDocument> mono = serviceClientRepository.findById(uuid);
        return mono.flatMap(client -> updateEnableClient(client, enable));
    }

    private Mono<? extends ServiceClientDocument> updateEnableClient(ServiceClientDocument client, boolean enable) {
        client.setEnabled(enable);
        return serviceClientRepository.save(client);
    }

    public Mono<ServiceClientDocument> enableTelegramBot(String uuid, boolean enable) {
        Mono<ServiceClientDocument> mono = serviceClientRepository.findById(uuid);
        return mono.flatMap(client -> updateTelegramBot(client, enable));
    }

    private Mono<? extends ServiceClientDocument> updateTelegramBot(ServiceClientDocument client, boolean enable) {
        client.setTelegramBotEnabled(enable);
        return serviceClientRepository.save(client);
    }
}
