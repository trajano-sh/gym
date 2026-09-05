package dev.trajano.gym.modules.auth.service;

import dev.trajano.gym.core.exception.AlreadyExistsException;
import dev.trajano.gym.modules.auth.dto.AuthRegisterRequestDTO;
import dev.trajano.gym.modules.user.model.User;
import dev.trajano.gym.modules.user.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AuthService {
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    public void register(AuthRegisterRequestDTO dto){
        if (dto.username() != null && userRepository.existsUsername(dto.username())){
            throw new AlreadyExistsException("Username Already exists");
        }
        User user =
    }

}
