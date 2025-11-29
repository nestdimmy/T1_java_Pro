package user_management.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import user_management.converter.ProductConverter;
import user_management.model.Product;
import user_management.model.dto.CreateProductDto;
import user_management.model.dto.ProductDto;
import user_management.service.ProductService;

import java.util.List;

@RestController
@RequestMapping("/product")
@RequiredArgsConstructor
public class ProductController {

    private final ProductService productService;
    private final ProductConverter productConverter;

    @PostMapping
    public ProductDto create(@RequestBody CreateProductDto createProduct) {
        Product product = productService.create(createProduct);
        return productConverter.convert(product);
    }

    @GetMapping("/{id}")
    public ProductDto findById(@PathVariable("id") Long id) {
        Product product = productService.findById(id);
        return productConverter.convert(product);
    }

    @GetMapping("/search")
    public List<ProductDto> search(@RequestParam("userId") Long userId) {
        List<Product> product = productService.findAllByUserId(userId);
        return productConverter.convert(product);
    }
}
