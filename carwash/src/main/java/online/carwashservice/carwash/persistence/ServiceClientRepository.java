package online.carwashservice.carwash.persistence;

import org.springframework.data.mongodb.repository.MongoRepository;

public interface ServiceClientRepository extends MongoRepository<ServiceClientDocument, String> {
}
