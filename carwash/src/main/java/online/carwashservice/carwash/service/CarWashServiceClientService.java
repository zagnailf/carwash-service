package online.carwashservice.carwash.service;

import online.carwashservice.carwash.dto.request.CreateCarWashServiceClientRq;
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
    public Mono<ServiceClientDocument> create(CreateCarWashServiceClientRq request) {
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
}
