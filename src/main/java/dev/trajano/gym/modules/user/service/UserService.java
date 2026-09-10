package dev.trajano.gym.modules.user.service;

import dev.trajano.gym.core.exception.BusinessException;
import dev.trajano.gym.core.exception.NotFoundException;
import dev.trajano.gym.modules.user.dto.UserRequestDTO;
import dev.trajano.gym.modules.user.dto.UserResponseDTO;
import dev.trajano.gym.modules.user.mapper.UserMapper;
import dev.trajano.gym.modules.user.domain.User;
import dev.trajano.gym.modules.user.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
@Slf4j
public class UserService {
    private final UserRepository userRepository;
    private final UserMapper userMapper;
    private final PasswordEncoder passwordEncoder;

    @Transactional(readOnly = true)
    public UserResponseDTO findUserById(Long userId) {
        User user = findById(userId);
        log.info("Listing user: {}",userId);
        return userMapper.fromEntity(user);
    }

    @Transactional(readOnly = true)
    public Page<UserResponseDTO> listUsers(Pageable pageable) {
        Page<User> users = userRepository.findAll(pageable);
        log.info("Listing users: {}", users.stream().count()+" Users");
        return users.map(userMapper::fromEntity);
    }

    @Transactional
    public UserResponseDTO update(Long userId, UserRequestDTO request){
        User user = findById(userId);
        if (passwordEncoder.matches(request.password(), user.getPassword())){
            throw new BusinessException("The password must be different from the previous one.");
        }
        User userUpdate = userMapper.toUpdate(user, request);
        userUpdate.setPassword(passwordEncoder.encode(request.password()));
        return userMapper.fromEntity(userUpdate);
    }

    @Transactional(readOnly = true)
    public void delete(Long userId) {
        User user = findById(userId);
        log.info("Deleting user: {}",userId);
        userRepository.delete(user);
    }

    private User findById(Long userId) {
        log.info("Searching user: {}",userId);
        return userRepository.findById(userId).orElseThrow(() -> new NotFoundException("User Not Found"));
    }

}