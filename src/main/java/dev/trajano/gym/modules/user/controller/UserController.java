package dev.trajano.gym.modules.user.controller;

import dev.trajano.gym.modules.user.dto.UserResponseDTO;
import dev.trajano.gym.modules.user.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/users")
public class UserController {
    private final UserService userService;

    @GetMapping("/{userId}")
    public ResponseEntity<UserResponseDTO> findUser(@PathVariable Long userId) {
        return ResponseEntity.ok(userService.findUserById(userId));
    }

    @GetMapping
    public ResponseEntity<Page<UserResponseDTO>> listUsers(@PageableDefault Pageable pageable) {
        return ResponseEntity.ok(userService.list(pageable));
    }

    @DeleteMapping("/{userId}")
    public ResponseEntity<Void> deleteUser(@PathVariable Long userId) {
        userService.delete(userId);
        return ResponseEntity.noContent().build();
    }
}
