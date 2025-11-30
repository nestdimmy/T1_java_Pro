package payments.config;

import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.boot.context.properties.ConfigurationProperties;

@Getter
@AllArgsConstructor
@ConfigurationProperties("integration.product")
public class ProductIntegrationProperties {
    private String url;
    private String getProductsByUserPath;
    private String updateBalancePath;
}
