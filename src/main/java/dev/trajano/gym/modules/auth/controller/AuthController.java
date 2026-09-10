package dev.trajano.gym.modules.auth.controller;

import dev.trajano.gym.modules.auth.dto.AuthLoginRequestDTO;
import dev.trajano.gym.modules.auth.dto.AuthRegisterRequestDTO;
import dev.trajano.gym.modules.auth.dto.TokenResponseDTO;
import dev.trajano.gym.modules.auth.service.AuthService;
import io.github.resilience4j.ratelimiter.annotation.RateLimiter;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/auth")
public class AuthController {
    private final AuthService authService;

    @PostMapping("/register")
    @RateLimiter(name = "authRegister")
    public ResponseEntity<Void> register(@RequestBody @Valid AuthRegisterRequestDTO request) {
        authService.register(request);
        return ResponseEntity.noContent().build();
    }

    @PostMapping("/login")
    @RateLimiter(name = "authLogin")
    public ResponseEntity<TokenResponseDTO> login(@RequestBody @Valid AuthLoginRequestDTO request) {
        TokenResponseDTO token = authService.login(request);
        return ResponseEntity.ok(token);
    }
}
