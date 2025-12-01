package payments.error_handling.exception;

import lombok.Getter;

@Getter
public class ProductInvalidBalanceIntegrationException extends RuntimeException {

    public ProductInvalidBalanceIntegrationException(String externalMessage) {
        super(externalMessage);
    }
}
