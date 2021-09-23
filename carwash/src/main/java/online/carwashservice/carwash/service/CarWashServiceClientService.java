package online.carwashservice.carwash.service;

import online.carwashservice.carwash.dto.request.CreateCarWashServiceClientRq;
import online.carwashservice.carwash.dto.result.CreateCarWashServiceClientResult;
import online.carwashservice.carwash.persistence.ServiceClientDocument;
import online.carwashservice.carwash.persistence.ServiceClientRepository;
import org.springframework.stereotype.Service;

@Service
public class CarWashServiceClientService {

    private final ServiceClientRepository serviceClientRepository;

    public CarWashServiceClientService(ServiceClientRepository serviceClientRepository) {
        this.serviceClientRepository = serviceClientRepository;
    }

    /**
     * Создание клиента сервиса. Доступ должен быть только у администратора/модератора сервиса
     */
    public CreateCarWashServiceClientResult create(CreateCarWashServiceClientRq request) {
        ServiceClientDocument result = serviceClientRepository.save(ServiceClientDocument.builder()
                .name(request.getName())
                .description(request.getDescription())
                .telegramBotEnabled(request.getTelegramBotEnabled())
                .build());

        return CreateCarWashServiceClientResult.builder().result(result).build();
    }
}
