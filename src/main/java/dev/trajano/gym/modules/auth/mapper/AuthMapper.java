package dev.trajano.gym.modules.auth.mapper;

import dev.trajano.gym.modules.auth.dto.AuthRegisterRequestDTO;
import dev.trajano.gym.modules.user.model.Role;
import dev.trajano.gym.modules.user.model.User;
import org.springframework.stereotype.Component;

import java.util.Set;

@Component
public class AuthMapper {
    public User toEntity(AuthRegisterRequestDTO dto){
        User user = new User();
        user.setRole(Role.BASIC);
        user.set
    }
}
