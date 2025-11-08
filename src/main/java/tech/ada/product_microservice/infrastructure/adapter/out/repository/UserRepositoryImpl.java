package tech.ada.product_microservice.infrastructure.adapter.out.repository;

import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;
import tech.ada.product_microservice.application.domain.User;
import tech.ada.product_microservice.application.port.out.repository.UserRepository;
import tech.ada.product_microservice.infrastructure.mapper.UserMapper;

@Component
@RequiredArgsConstructor
public class UserRepositoryImpl implements UserRepository {

    private final UserEntityRepository userEntityRepository;
    private final UserMapper userMapper;

    @Override
    public User save(User user) {
        this.userEntityRepository.save(this.userMapper.toEntity(user));
        return user;
    }

    @Override
    public UserDetails findByUsername(String username) throws UsernameNotFoundException {
        return this.userEntityRepository.findByUsername(username)
                .orElseThrow(() -> new UsernameNotFoundException("User not found: " + username));
    }
}
