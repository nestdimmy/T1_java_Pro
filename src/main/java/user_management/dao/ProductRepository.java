package user_management.dao;

import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import user_management.model.Product;

import java.util.List;

public interface ProductRepository extends JpaRepository<Product, Long> {

    @EntityGraph(attributePaths = "user")
    @Query("SELECT p from Product p where p.user.id = :userId")
    List<Product> findAllByUserId(@Param("userId") Long userId);
}
