package payments.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import payments.model.PaymentRequestDto;
import payments.model.product.ProductDto;
import payments.model.product.update.BalanceOperation;
import payments.model.product.update.UpdateBalanceDto;

@Service
@RequiredArgsConstructor
public class PaymentService {

    private final ProductIntegrationService productIntegrationService;

    public ProductDto execute(PaymentRequestDto request) {
        UpdateBalanceDto body = new UpdateBalanceDto();
        body.setAmount(request.amount());
        body.setOperation(BalanceOperation.MINUS);

        return productIntegrationService.updateBalance(request.productId(), body);
    }
}
