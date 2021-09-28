package online.carwashservice.carwash.configuration;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Contact;
import io.swagger.v3.oas.models.info.Info;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class SwaggerConfiguration {

    @Bean
    public OpenAPI openAPIV1(@Value("${springdoc.info.title}") String title,
                             @Value("${springdoc.info.description}") String description,
                             @Value("${springdoc.info.version}") String version,
                             @Value("${springdoc.info.contact.email}") String email,
                             @Value("${springdoc.info.contact.url}") String url,
                             @Value("${springdoc.info.contact.name}") String name) {
        return new OpenAPI()
                .info(new Info().title(title).description(description).version(version)
                        .contact(new Contact().email(email).url(url).name(name)));
    }
}
