package dev.trajano.gym.core.config;


import dev.trajano.gym.modules.user.domain.Role;
import dev.trajano.gym.modules.user.domain.User;
import dev.trajano.gym.modules.user.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.CommandLineRunner;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

@Component
@Slf4j
@RequiredArgsConstructor
public class AdminConfig implements CommandLineRunner {
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    @Value("${adm.username}")
    private String username;

    @Value("${adm.password}")
    private String password;

    @Override
    public void run(String... args) throws Exception {
        log.info("validating super admin");

        if (userRepository.existsByUsername(username)) {
            log.info("admin already exists");
        } else {
            User user = new User();
            user.setUsername(username);
            user.setPassword(passwordEncoder.encode(password));
            user.setRole(Role.ADMIN);
            userRepository.save(user);
            System.out.println("super admin created");
        }
    }
}
