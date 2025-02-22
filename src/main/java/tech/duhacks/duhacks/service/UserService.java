package tech.duhacks.duhacks.service;

import jakarta.persistence.EntityNotFoundException;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import tech.duhacks.duhacks.dto.UserReq;
import tech.duhacks.duhacks.exception.AuthException;
import tech.duhacks.duhacks.model.User;
import tech.duhacks.duhacks.repository.UserRepo;

@AllArgsConstructor
@Service
public class UserService {

    private final UserRepo userRepo;

    private final UserMapper userMapper;

    public User login(UserReq userReq) {
        userRepo.findOneByEmail(userReq.email()).ifPresent(user -> {
            throw new AuthException("User with email %s already exists.".formatted(userReq.email()));
        });

        userRepo.save(userMapper.getUser(userReq));
        return userRepo.findOneByEmail(userReq.email()).orElse(null);
    }

    public User signIn(UserReq userReq){
        var user = userRepo.findOneByEmail(userReq.email()).orElseThrow(() -> new EntityNotFoundException("User with email %s not found".formatted(userReq.email())));

        if(!user.getPassword().equals(userReq.password()) ){
            throw  new AuthException("Password Mismatch");
        }

        return user;
    }

    public void delete(Long id) {
        userRepo.deleteById(id);
    }

}
