package online.carwashservice.carwash.configuration;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Contact;
import io.swagger.v3.oas.models.info.Info;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class SwaggerConfiguration {

    @Bean
    public OpenAPI openAPI() {
        return new OpenAPI().info(info());
    }

    private Info info() {
        return new Info()
                .title("API для клиентов системы управления автомойками")
                .version("1.0.0")
                .contact(contact());
    }

    private Contact contact() {
        return new Contact()
                .email("support@carwashservice.online")
                .url("https://carwashservice.online")
                .name("Zagidulin Nail");
    }
}
