package user_management.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import user_management.model.User;

import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Long> {
    boolean existsByName(String name);
    Optional<User> findByName(String name);
}
