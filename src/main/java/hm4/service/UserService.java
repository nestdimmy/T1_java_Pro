package hm4.service;

import hm4.dao.UserDao;
import hm4.model.User;
import org.springframework.stereotype.Service;

@Service
public class UserService {

    private final UserDao userDao;

    public UserService(UserDao userDao) {
        this.userDao = userDao;
    }

    public User create(String name) {
        if (existByName(name)) {
            throw new IllegalStateException("Пользователь с таким именем уже сузетсвует");
        }
        return userDao.insert(new User(name));
    }

    public void update(User user) {
        userDao.update(user);
    }

    public User findById(Long id) {
        return userDao.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Пользователь не найден"));
    }

    public boolean existByName(String name) {
        return userDao.existByName(name);
    }

    public void deleteById(Long id) {
        userDao.deleteById(id);
    }

    public void cleanup() {
        userDao.deleteAll();
    }
}
