package payments.error_handling.exception;

public class ProductNotFoundIntegrationException extends RuntimeException {

    public ProductNotFoundIntegrationException() {
        super("Продукт не найден в каталоге продуктов");
    }
}
