package products.service;

import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import products.dao.ProductRepository;
import products.dao.UserRepository;
import products.error_handling.InvalidBalanceException;
import products.model.BalanceOperation;
import products.model.Product;
import products.model.User;
import products.model.dto.CreateProductDto;
import products.model.dto.UpdateBalanceDto;

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
                .orElseThrow(() -> new EntityNotFoundException("Пользователь не найден"));

        result.setUser(user);

        return productRepository.save(result);
    }

    public List<Product> findAllByUserId(Long userId) {
        return productRepository.findAllByUserId(userId);
    }

    public Product findById(Long id) {
        return productRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Продукт не найден"));
    }


    public Product updateBalance(Long id, UpdateBalanceDto request) {
        Product product = findById(id);

        if (BalanceOperation.MINUS.equals(request.getOperation())) {
            if (product.getBalance().compareTo(request.getAmount()) < 0) {
                throw new InvalidBalanceException(product.getNumber());
            } else {
                product.setBalance(product.getBalance().subtract(request.getAmount()));
                return productRepository.save(product);
            }
        } else {
            product.setBalance(product.getBalance().add(request.getAmount()));
            return productRepository.save(product);
        }
    }
}
