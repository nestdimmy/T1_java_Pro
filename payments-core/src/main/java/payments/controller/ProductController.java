package payments.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import payments.model.product.ProductDto;
import payments.service.ProductIntegrationService;

import java.util.List;

@RestController
@RequestMapping("/api/v1/products")
@RequiredArgsConstructor
public class ProductController {

    private final ProductIntegrationService productIntegrationService;

    @GetMapping("/search")
    public List<ProductDto> search(@RequestParam("userId") Long userId) {
        return productIntegrationService.fetchUserProducts(userId);
    }
}
