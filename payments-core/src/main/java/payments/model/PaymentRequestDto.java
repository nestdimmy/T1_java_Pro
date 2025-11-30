package payments.model;

import java.math.BigDecimal;

public record PaymentRequestDto(
        Long productId,
        BigDecimal amount
) {
}
