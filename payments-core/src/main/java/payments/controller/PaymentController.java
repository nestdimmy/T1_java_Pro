package payments.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import payments.model.PaymentRequestDto;
import payments.model.product.ProductDto;
import payments.service.PaymentService;
import payments.service.ProductIntegrationService;

@RestController
@RequestMapping("/api/v1/payments")
@RequiredArgsConstructor
public class PaymentController {

    private final PaymentService paymentService;
    private final ProductIntegrationService productIntegrationService;

    @PostMapping("/execute")
    public ProductDto executePayment(@RequestBody PaymentRequestDto request) {
        return paymentService.execute(request);
    }
}
