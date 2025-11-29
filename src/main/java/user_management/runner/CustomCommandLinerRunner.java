package user_management.runner;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Service;
import user_management.dao.DepartmentRepository;
import user_management.dao.UserRepository;

@Slf4j
@Service
@RequiredArgsConstructor
public class CustomCommandLinerRunner implements CommandLineRunner {

    private final UserRepository userRepository;
    private final TransactionSelector transactionSelector;
    private final DepartmentRepository departmentRepository;

    @Override
    public void run(String... args) throws Exception {
        transactionSelector.transactionRequest();

        userRepository.findAll()
                .forEach(user -> log.info(user.toString()));

        userRepository.findByName("Дима").ifPresent(user -> log.info(user.toString()));
        if (userRepository.existsByName("Вася")) {
            log.info("Пользователь с именем Вася уже существует");
        }

        log.info("With graph");
        departmentRepository.findAllWithUsers()
                .forEach(dep -> log.info(dep.toString()));
    }
}
