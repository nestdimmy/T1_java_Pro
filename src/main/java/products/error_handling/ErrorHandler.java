package products.error_handling;

import jakarta.persistence.EntityNotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@ControllerAdvice
public class ErrorHandler extends ResponseEntityExceptionHandler {

    @ExceptionHandler(EntityNotFoundException.class)
    public ResponseEntity<CommonErrorResponseDto> handleNotFound(EntityNotFoundException ex) {
        CommonErrorResponseDto result = new CommonErrorResponseDto(ex.getMessage());
        return new ResponseEntity<>(result, HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(InvalidBalanceException.class)
    public ResponseEntity<CommonErrorResponseDto> handleNotFound(InvalidBalanceException ex) {
        CommonErrorResponseDto result = new CommonErrorResponseDto(ex.getMessage());
        return new ResponseEntity<>(result, HttpStatus.UNPROCESSABLE_ENTITY);
    }
}
