package payments.config;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestTemplate;

import java.time.Duration;
import java.util.concurrent.TimeUnit;

@Configuration
@EnableConfigurationProperties(ProductIntegrationProperties.class)
public class RestTemplateConfiguration {

    @Value("${integration.product.url}")
    private String rootUrl;

    @Bean
    @Qualifier("productRestTemplate")
    public RestTemplate productRestTemplate(ProductErrorHandler errorHandler) {
        return new RestTemplateBuilder()
                .rootUri(rootUrl)
                .errorHandler(errorHandler)
                .setConnectTimeout(Duration.of(10, TimeUnit.SECONDS.toChronoUnit()))
                .setReadTimeout(Duration.of(10, TimeUnit.SECONDS.toChronoUnit()))
                .build();
    }
}
