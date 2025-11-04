package tech.ada.product_microservice.service;

import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import tech.ada.product_microservice.dto.UserDTO;
import tech.ada.product_microservice.model.User;
import tech.ada.product_microservice.repository.UserRepository;

@Service
@RequiredArgsConstructor
public class UserService implements UserDetailsService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        return this.userRepository.findByUsername(username)
                .orElse(null);
    }

    public UserDTO register(UserDTO userDTO) {
        User user = new User(userDTO.getUsername(), passwordEncoder.encode(userDTO.getPassword()), userDTO.getRole());
        this.userRepository.save(user);
        return userDTO;
    }

}
