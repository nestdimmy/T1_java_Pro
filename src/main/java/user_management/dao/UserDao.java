package user_management.dao;

import user_management.model.User;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Optional;

//@Repository
@Deprecated
public class UserDao {

    private final DataSource dataSource;

    private static final String INSERT_STATEMENT = "INSERT INTO USERS(name) VALUES(?) RETURNING id";
    private static final String UPDATE_STATEMENT = "UPDATE USERS SET name = ? WHERE id = ?";
    private static final String SELECT_STATEMENT = "SELECT * FROM USERS WHERE id = ?";
    private static final String DELETE_STATEMENT = "DELETE FROM USERS WHERE id = ?";
    private static final String EXISTS_STATEMENT = "SELECT EXISTS(SELECT 1 FROM users WHERE name=?)";
    private static final String CLEANUP_STATEMENT = "DELETE FROM USERS";

    public UserDao(DataSource dataSource) {
        this.dataSource = dataSource;
    }

    public User insert(User user) {
        try (Connection conn = dataSource.getConnection();
             PreparedStatement insertQuery = conn.prepareStatement(INSERT_STATEMENT, Statement.RETURN_GENERATED_KEYS)) {
            insertQuery.setString(1, user.getName());
            insertQuery.executeUpdate();

            ResultSet result = insertQuery.getGeneratedKeys();
            if (result.next()) {
                user.setId(result.getLong(1));
            }

            return user;
        } catch (SQLException e) {
            throw new RuntimeException("Ошибка создания пользователя", e);
        }
    }

    public Optional<User> findById(Long id) {
        try (Connection conn = dataSource.getConnection();
             PreparedStatement query = conn.prepareStatement(SELECT_STATEMENT)) {
            query.setLong(1, id);

            ResultSet resultSet = query.executeQuery();
            if (resultSet.next()) {
                User user = new User();
                user.setId(resultSet.getLong("id"));
                user.setName(resultSet.getString("name"));
                return Optional.of(user);
            }

            return Optional.empty();
        } catch (SQLException e) {
            throw new RuntimeException("Ошибка поиска пользователя по id", e);
        }
    }

    public void update(User user) {
        try (Connection conn = dataSource.getConnection();
             PreparedStatement query = conn.prepareStatement(UPDATE_STATEMENT)) {
            query.setString(1, user.getName());
            query.setLong(2, user.getId());
            query.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException("Ошибка обновления пользователя", e);
        }
    }

    public void deleteById(Long id) {
        try (Connection conn = dataSource.getConnection();
             PreparedStatement query = conn.prepareStatement(DELETE_STATEMENT)) {
            query.setLong(1, id);
            query.execute();
        } catch (SQLException e) {
            throw new RuntimeException("Ошибка удаления пользователя по идентификатору", e);
        }
    }

    public boolean existByName(String name) {
        try (Connection conn = dataSource.getConnection();
             PreparedStatement query = conn.prepareStatement(EXISTS_STATEMENT)) {
            query.setString(1, name);
            ResultSet resultSet = query.executeQuery();
            if (resultSet.next()) {
                return resultSet.getBoolean(1);
            }

            return false;
        } catch (SQLException e) {
            throw new RuntimeException("Ошибка поиска пользователя по имени", e);
        }
    }

    public void deleteAll() {
        try (Connection conn = dataSource.getConnection();
             PreparedStatement query = conn.prepareStatement(CLEANUP_STATEMENT)) {
            query.execute();
        } catch (SQLException e) {
            throw new RuntimeException("Ошибка удаления пользователей", e);
        }
    }
}
