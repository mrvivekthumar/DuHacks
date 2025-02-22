package tech.duhacks.duhacks.service;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import tech.duhacks.duhacks.model.User;
import tech.duhacks.duhacks.repository.UserRepo;

@AllArgsConstructor
@Service
public class UserService {

    private final UserRepo userRepo;

    public void save(User user) {
        userRepo.save(user);
    }

    public void delete(Long id) {
        userRepo.deleteById(id);
    }

}
