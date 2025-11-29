package user_management.runner;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import user_management.dao.UserRepository;

@Service
@RequiredArgsConstructor
public class TransactionSelector {
    private final UserRepository userRepository;

    @Transactional
    public void transactionRequest() {
        userRepository.findById(41L);
        userRepository.findById(41L);
        userRepository.findById(41L);
        userRepository.findById(41L);
        userRepository.findById(41L);
    }
}
