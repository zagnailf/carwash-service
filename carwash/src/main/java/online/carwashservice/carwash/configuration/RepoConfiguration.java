package online.carwashservice.carwash.configuration;

import lombok.RequiredArgsConstructor;
import org.springframework.data.mongodb.repository.config.EnableReactiveMongoRepositories;
import org.springframework.stereotype.Component;

@Component
@EnableReactiveMongoRepositories("online.carwashservice.carwash.persistence")
@RequiredArgsConstructor
public class RepoConfiguration {
}
