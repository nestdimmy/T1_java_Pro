package payments.error_handling.handler;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;
import payments.error_handling.dto.ProductIntegrationErrorDto;
import payments.error_handling.exception.ProductIntegrationException;
import payments.error_handling.exception.ProductInvalidBalanceIntegrationException;
import payments.error_handling.exception.ProductNotFoundIntegrationException;

@ControllerAdvice
public class ErrorHandler extends ResponseEntityExceptionHandler {

    @ExceptionHandler(ProductIntegrationException.class)
    public ResponseEntity<ProductIntegrationErrorDto> handleNotFound(ProductIntegrationException ex) {
        ProductIntegrationErrorDto result = new ProductIntegrationErrorDto(ex.getMessage());
        return new ResponseEntity<>(result, HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @ExceptionHandler(ProductNotFoundIntegrationException.class)
    public ResponseEntity<ProductIntegrationErrorDto> handleNotFound(ProductNotFoundIntegrationException ex) {
        ProductIntegrationErrorDto result = new ProductIntegrationErrorDto(ex.getMessage());
        return new ResponseEntity<>(result, HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(ProductInvalidBalanceIntegrationException.class)
    public ResponseEntity<ProductIntegrationErrorDto> handleNotFound(ProductInvalidBalanceIntegrationException ex) {
        ProductIntegrationErrorDto result = new ProductIntegrationErrorDto(ex.getMessage());
        return new ResponseEntity<>(result, HttpStatus.UNPROCESSABLE_ENTITY);
    }
}
