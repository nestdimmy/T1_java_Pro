package products.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import products.model.User;

import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Long> {
    boolean existsByName(String name);
    Optional<User> findByName(String name);
}
