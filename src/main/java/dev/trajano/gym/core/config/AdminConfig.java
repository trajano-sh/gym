package dev.trajano.gym.core.config;


import dev.trajano.gym.modules.user.domain.Role;
import dev.trajano.gym.modules.user.domain.User;
import dev.trajano.gym.modules.user.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.CommandLineRunner;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

@Component
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
        System.out.print("Validating Super Admin\n");

        if (userRepository.existsByUsername(username)) {
            System.out.print("Super Admin Already Exists\n");
        } else {
            User user = new User();
            user.setUsername(username);
            user.setPassword(passwordEncoder.encode(password));
            user.setRole(Role.ADMIN);
            userRepository.save(user);
            System.out.println("Super Admin Created");
        }
    }
}
