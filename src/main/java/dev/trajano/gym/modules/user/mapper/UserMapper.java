package dev.trajano.gym.modules.user.mapper;

import dev.trajano.gym.modules.user.dto.UserRequestDTO;
import dev.trajano.gym.modules.user.dto.UserResponseDTO;
import dev.trajano.gym.modules.user.model.User;
import org.springframework.stereotype.Component;

@Component
public class UserMapper {
    public User toUpdate(User user, UserRequestDTO requestDTO){
        user.setUsername(requestDTO.username());
        return user;
    }

    public UserResponseDTO fromEntity(User user){
        return new UserResponseDTO(
                user.getId(),
                user.getUsername(),
                user.getRole().toString(),
                user.getCreatedAt(),
                user.getUpdateAt()
        );
    }
}
