package products.error_handling;

public class InvalidBalanceException extends RuntimeException {

    public InvalidBalanceException(String productNumber) {
        super("Недосостаточно средст на счете: " + productNumber);
    }
}
