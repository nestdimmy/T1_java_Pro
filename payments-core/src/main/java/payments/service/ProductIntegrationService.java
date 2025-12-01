package payments.service;

import lombok.RequiredArgsConstructor;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;
import payments.config.ProductIntegrationProperties;
import payments.model.product.ProductDto;
import payments.model.product.update.UpdateBalanceDto;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ProductIntegrationService {

    private final RestTemplate productRestTemplate;
    private final ProductIntegrationProperties properties;

    public List<ProductDto> fetchUserProducts(Long userId) {
        return productRestTemplate.exchange(
                UriComponentsBuilder.fromHttpUrl(
                                properties.getUrl() + properties.getGetProductsByUserPath()
                        ).queryParam("userId", userId.toString())
                        .build().toUri(),
                HttpMethod.GET,
                HttpEntity.EMPTY,
                new ParameterizedTypeReference<List<ProductDto>>() {
                }
        ).getBody();
    }

    public ProductDto updateBalance(Long productId, UpdateBalanceDto body) {
        return productRestTemplate.postForObject(
                UriComponentsBuilder.fromHttpUrl(
                        properties.getUrl() + properties.getUpdateBalancePath()
                ).build(productId),
                body,
                ProductDto.class
        );
    }
}
