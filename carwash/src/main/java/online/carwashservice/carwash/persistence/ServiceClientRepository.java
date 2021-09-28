package online.carwashservice.carwash.persistence;

import org.springframework.data.mongodb.repository.ReactiveMongoRepository;
import reactor.core.publisher.Flux;

public interface ServiceClientRepository extends ReactiveMongoRepository<ServiceClientDocument, String> {
    Flux<ServiceClientDocument> findByEnabled(boolean b);
}
