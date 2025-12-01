package payments.config;

import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.client.ClientHttpResponse;
import org.springframework.stereotype.Component;
import org.springframework.web.client.ResponseErrorHandler;
import payments.error_handling.dto.ProductIntegrationErrorDto;
import payments.error_handling.exception.ProductIntegrationException;
import payments.error_handling.exception.ProductInvalidBalanceIntegrationException;
import payments.error_handling.exception.ProductNotFoundIntegrationException;

import java.io.IOException;

@Component
@RequiredArgsConstructor
public class ProductErrorHandler implements ResponseErrorHandler {

    private final ObjectMapper objectMapper;

    @Override
    public boolean hasError(ClientHttpResponse response) throws IOException {
        return !response.getStatusCode().is2xxSuccessful();
    }

    @Override
    public void handleError(ClientHttpResponse response) throws IOException {
        if (response.getStatusCode().equals(HttpStatus.NOT_FOUND)) {
            throw new ProductNotFoundIntegrationException();
        }

        ProductIntegrationErrorDto errorResponse = objectMapper.readValue(
                response.getBody(), ProductIntegrationErrorDto.class);
        if (response.getStatusCode().is5xxServerError()) {
            throw new ProductIntegrationException(errorResponse.message());
        } else if (response.getStatusCode().equals(HttpStatus.UNPROCESSABLE_ENTITY)) {
            throw new ProductInvalidBalanceIntegrationException(errorResponse.message());
        }
    }
}
