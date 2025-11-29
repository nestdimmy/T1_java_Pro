package user_management.service;

import org.springframework.stereotype.Service;
import user_management.dao.UserRepository;
import user_management.model.User;

@Service
public class UserService {

    private final UserRepository userRepository;

    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public User create(String name) {
        if (existByName(name)) {
            throw new IllegalStateException("Пользователь с таким именем уже сущетсвует");
        }
        return userRepository.save(new User(name));
    }

    public void update(User user) {
        userRepository.save(user);
    }

    public User findById(Long id) {
        return userRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Пользователь не найден"));
    }

    public boolean existByName(String name) {
        return userRepository.existsByName(name);
    }

    public void deleteById(Long id) {
        userRepository.deleteById(id);
    }

    public void cleanup() {
        userRepository.deleteAll();
    }
}
