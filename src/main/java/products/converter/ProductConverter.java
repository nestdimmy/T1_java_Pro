package products.converter;

import org.springframework.stereotype.Component;
import products.model.Product;
import products.model.dto.ProductDto;

import java.util.List;

@Component
public class ProductConverter {

    public List<ProductDto> convert(List<Product> entities) {
        return entities.stream().map(this::convert).toList();
    }

    public ProductDto convert(Product entity) {
        ProductDto dto = new ProductDto();
        dto.setId(entity.getId());
        dto.setType(entity.getType());
        dto.setBalance(entity.getBalance());
        dto.setNumber(entity.getNumber());
        dto.setUserId(entity.getUser().getId());
        return dto;
    }
}
