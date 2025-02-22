package tech.duhacks.duhacks.service;

import org.springframework.stereotype.Service;
import tech.duhacks.duhacks.dto.UserReq;
import tech.duhacks.duhacks.dto.UserRes;
import tech.duhacks.duhacks.model.User;

@Service
public class UserMapper {

    public User getUser(UserReq userReq){
        return  User.builder()
                .email(userReq.email())
                .name(userReq.name())
                .password(userReq.password())
                .build();
    }

    public UserRes getUserRes (User user){
        return new UserRes(user.getId());
    }
}
