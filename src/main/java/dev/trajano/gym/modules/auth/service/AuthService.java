package dev.trajano.gym.modules.auth.service;

import dev.trajano.gym.core.exception.AlreadyExistsException;
import dev.trajano.gym.core.exception.BadCredentialsException;
import dev.trajano.gym.core.exception.NotFoundException;
import dev.trajano.gym.core.security.TokenProvider;
import dev.trajano.gym.modules.auth.dto.AuthLoginRequestDTO;
import dev.trajano.gym.modules.auth.dto.AuthRegisterRequestDTO;
import dev.trajano.gym.modules.auth.dto.ResetPasswordRequestDTO;
import dev.trajano.gym.modules.auth.dto.TokenResponseDTO;
import dev.trajano.gym.modules.auth.mapper.AuthMapper;
import dev.trajano.gym.modules.user.domain.User;
import dev.trajano.gym.modules.user.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.InternalAuthenticationServiceException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

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

    @Transactional
    public void register(AuthRegisterRequestDTO dto) {
        if (userRepository.findByUsername(dto.username()).isPresent())
            throw new AlreadyExistsException("Username already exists");

        User user = authMapper.toEntity(dto);
        user.setPassword(passwordEncoder.encode(dto.password()));
        userRepository.save(user);
    }

    @Transactional
    public TokenResponseDTO login(AuthLoginRequestDTO dto) {
        try {
            Authentication authentication = authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(dto.username(), dto.password())
            );

            String token = tokenProvider.generateToken(authentication);

            return authMapper.toResponse(token, "Bearer", expirationTime);

        } catch (InternalAuthenticationServiceException e) {
            if (e.getCause() instanceof NotFoundException) {
                throw new NotFoundException("User Not Found");
            }
            throw new BadCredentialsException("Credentials invalids");

        } catch (org.springframework.security.authentication.BadCredentialsException e) {
            throw new BadCredentialsException("Credentials invalids");
        }
    }

    @Transactional
    public void resetPassword(User user, ResetPasswordRequestDTO dto) {
        if (!passwordEncoder.matches(dto.passwordActual(), user.getPassword())) {
            throw new BadCredentialsException("Credentials Invalids");
        }
        if (passwordEncoder.matches(dto.newPassword(), user.getPassword())) {
            throw new BadCredentialsException("Credentials Invalids");
        }
        if (!dto.newPassword().equals(dto.confirmNewPassword())) {
            throw new dev.trajano.gym.core.exception.BadCredentialsException("Credentials invalids");
        }

        String encodedPassword = passwordEncoder.encode(dto.newPassword());
        user.setPassword(encodedPassword);
        userRepository.save(user);
    }
}
