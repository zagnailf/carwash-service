package online.carwashservice.carwash.configuration.properties;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;

@Data
@ConfigurationProperties(prefix = "springdoc.info")
public class SwaggerProperties {
    private String title;
    private String description;
    private String version;
    private SwaggerContactProperties contact;

    @Data
    public static class SwaggerContactProperties {
        private String name;
        private String email;
        private String url;
    }
}
