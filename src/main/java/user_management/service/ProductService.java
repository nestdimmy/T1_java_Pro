package user_management.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import user_management.dao.ProductRepository;
import user_management.dao.UserRepository;
import user_management.model.Product;
import user_management.model.User;
import user_management.model.dto.CreateProductDto;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ProductService {

    private final ProductRepository productRepository;
    private final UserRepository userRepository;

    public Product create(CreateProductDto request) {
        Product result = new Product();
        result.setBalance(request.getBalance());
        result.setType(request.getType());
        result.setNumber(request.getNumber());

        User user = userRepository.findById(request.getUserId())
                .orElseThrow(() -> new RuntimeException("Пользователь не найден"));

        result.setUser(user);

        return productRepository.save(result);
    }

    public List<Product> findAllByUserId(Long userId) {
        return productRepository.findAllByUserId(userId);
    }

    public Product findById(Long id) {
        return productRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Продукт не найден"));
    }
}
