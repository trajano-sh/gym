package dev.trajano.gym.modules.user.service;

import dev.trajano.gym.core.exception.NotFoundException;
import dev.trajano.gym.modules.user.dto.UserResponseDTO;
import dev.trajano.gym.modules.user.mapper.UserMapper;
import dev.trajano.gym.modules.user.model.User;
import dev.trajano.gym.modules.user.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserService {
    private final UserRepository userRepository;
    private final UserMapper userMapper;

    public UserResponseDTO findUserById(Long userId) {
        User user = findById(userId);
        return userMapper.fromEntity(user);
    }

    public Page<UserResponseDTO> list(Pageable pageable) {
        Page<User> users = userRepository.findAll(pageable);
        return users.map(userMapper::fromEntity);
    }

    public void delete(Long userId) {
        User user = findById(userId);
        userRepository.delete(user);
    }

    private User findById(Long userId) {
        User user = userRepository.findById(userId).orElseThrow(() -> new NotFoundException("User Not Found"));
        return user;
    }
}