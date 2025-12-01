package products.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.NamedAttributeNode;
import jakarta.persistence.NamedEntityGraph;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Set;


@NamedEntityGraph(
        name = "dep.with-users",
        attributeNodes = {
                @NamedAttributeNode(value = "users")
        }
)
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "departments")
public class Department {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column(name = "name", nullable = false)
    private String name;

    @OneToMany(mappedBy = "department")
    private Set<User> users;

    public Department(String name) {
        this.name = name;
    }

    public String toString() {
        return "Department{"
                + "\"id\": " + this.getId() +
                ", \"name\": \"" + this.getName() +
                ", \"users\": \"" + this.getUsers() +
                "\"}";
    }
}
