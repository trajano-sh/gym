package dev.trajano.gym.modules.user.service;

import dev.trajano.gym.core.exception.NotFoundException;
import dev.trajano.gym.modules.user.dto.UserResponseDTO;
import dev.trajano.gym.modules.user.mapper.UserMapper;
import dev.trajano.gym.modules.user.domain.User;
import dev.trajano.gym.modules.user.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class UserService {
    private final UserRepository userRepository;
    private final UserMapper userMapper;

    @Transactional(readOnly = true)
    public UserResponseDTO findUserById(Long userId) {
        User user = findById(userId);
        return userMapper.fromEntity(user);
    }

    @Transactional(readOnly = true)
    public Page<UserResponseDTO> listUsers(Pageable pageable) {
        Page<User> users = userRepository.findAll(pageable);
        return users.map(userMapper::fromEntity);
    }

    @Transactional(readOnly = true)
    public void delete(Long userId) {
        User user = findById(userId);
        userRepository.delete(user);
    }

    private User findById(Long userId) {
        User user = userRepository.findById(userId).orElseThrow(() -> new NotFoundException("User Not Found"));
        return user;
    }

}