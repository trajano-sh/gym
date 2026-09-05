package dev.trajano.gym.modules.auth.service;

import dev.trajano.gym.core.security.TokenProvider;
import dev.trajano.gym.modules.auth.dto.AuthLoginRequestDTO;
import dev.trajano.gym.modules.auth.dto.AuthRegisterRequestDTO;
import dev.trajano.gym.modules.auth.dto.TokenResponseDTO;
import dev.trajano.gym.modules.auth.mapper.AuthMapper;
import dev.trajano.gym.modules.user.model.User;
import dev.trajano.gym.modules.user.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.apache.coyote.BadRequestException;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AuthService {
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final AuthenticationManager authenticationManager;
    private final TokenProvider tokenProvider;
    private final AuthMapper authMapper;

    @Value("${jwt.expiration}")
    private long expirationTime;

    public void register(AuthRegisterRequestDTO dto) throws BadRequestException {
        if (userRepository.findByUsername(dto.username())
                .isPresent()) throw new BadRequestException("Username already exists");

        User user = authMapper.toEntity(dto);
        user.setPassword(passwordEncoder.encode(dto.password()));
        userRepository.save(user);
    }

    public TokenResponseDTO login(AuthLoginRequestDTO dto) {
        try {
            Authentication authentication = authenticationManager
                    .authenticate(new UsernamePasswordAuthenticationToken(dto.username(), dto.password()));

            String token = tokenProvider.generateToken(authentication);

            return authMapper.toResponse(token, "Bearer", expirationTime);

        } catch (BadCredentialsException e) {
            throw new BadCredentialsException("Credentials Invalids");
        }
    }
}
