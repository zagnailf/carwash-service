package online.carwashservice.carwash.configuration;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Contact;
import io.swagger.v3.oas.models.info.Info;
import online.carwashservice.carwash.configuration.properties.SwaggerProperties;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;

@Configuration
@EnableConfigurationProperties(SwaggerProperties.class)
@Profile({"dev", "container"})
public class SwaggerConfiguration {

    @Bean
    public OpenAPI openAPIV1(SwaggerProperties info) {
        return new OpenAPI()
                .info(new Info()
                        .title(info.getTitle())
                        .description(info.getDescription())
                        .version(info.getVersion())
                        .contact(new Contact()
                                .email(info.getContact().getEmail())
                                .url(info.getContact().getUrl())
                                .name(info.getContact().getName())));
    }
}
