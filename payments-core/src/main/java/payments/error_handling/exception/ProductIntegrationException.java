package payments.error_handling.exception;

public class ProductIntegrationException extends RuntimeException {

    public ProductIntegrationException(String cause) {
        super(cause);
    }
}
