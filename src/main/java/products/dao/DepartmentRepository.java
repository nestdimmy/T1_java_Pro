package products.dao;

import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import products.model.Department;

import java.util.Set;

public interface DepartmentRepository extends JpaRepository<Department, Long> {

    @EntityGraph(attributePaths = "users")
    @Query("SELECT d from Department d")
    Set<Department> findAllWithUsers();

}
