package dev.trajano.gym.modules.auth.mapper;

import dev.trajano.gym.modules.auth.dto.AuthRegisterRequestDTO;
import dev.trajano.gym.modules.auth.dto.TokenResponseDTO;
import dev.trajano.gym.modules.user.domain.Role;
import dev.trajano.gym.modules.user.domain.User;
import org.springframework.stereotype.Component;

@Component
public class AuthMapper {
    public User toEntity(AuthRegisterRequestDTO dto) {
        User user = new User();
        user.setRole(Role.BASIC);
        user.setUsername(dto.username());
        return user;
    }

    public TokenResponseDTO toResponse(String token,
                                       String typeToken,
                                       Long expiration
    ) {
        return new TokenResponseDTO(token, typeToken, expiration);
    }
}