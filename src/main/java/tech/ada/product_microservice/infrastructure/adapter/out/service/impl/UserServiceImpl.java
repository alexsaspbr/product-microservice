package tech.ada.product_microservice.infrastructure.adapter.out.service.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import tech.ada.product_microservice.application.domain.User;
import tech.ada.product_microservice.application.port.in.service.UserService;
import tech.ada.product_microservice.application.port.out.repository.UserRepository;
import tech.ada.product_microservice.infrastructure.mapper.UserMapper;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final UserMapper userMapper;

    public User register(User user) {
        user.setPassword(this.passwordEncoder.encode(user.getPassword()));
        return this.userRepository.save(user);
    }

}
