package br.com.asa.product_microservice.infrastructure.adapter.in.web.controller;

import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import br.com.asa.product_microservice.application.domain.User;
import br.com.asa.product_microservice.application.domain.exception.TokenInvalidException;
import br.com.asa.product_microservice.application.port.in.service.TokenService;
import br.com.asa.product_microservice.application.port.in.service.UserService;
import br.com.asa.product_microservice.infrastructure.adapter.out.repository.entity.UserEntity;
import br.com.asa.product_microservice.infrastructure.dto.UserDTO;
import br.com.asa.product_microservice.infrastructure.dto.UserResponseDTO;
import br.com.asa.product_microservice.infrastructure.mapper.UserMapper;

@Slf4j
@RestController
@RequestMapping("/auth")
@Tag(name = "Authentication")
@RequiredArgsConstructor
public class AuthController {

    private final UserService userService;
    private final UserDetailsService authService;
    private final TokenService jwtService;
    private final UserMapper userMapper;
    private final AuthenticationManager authenticationManager;

    @PostMapping("/login")
    public ResponseEntity<UserResponseDTO> login(@RequestBody UserDTO userDTO) throws
            TokenInvalidException {

        var usernamePasswordAuthenticationToken = new
                UsernamePasswordAuthenticationToken(
                userDTO.getUsername(),
                userDTO.getPassword());

        var authentication = this.authenticationManager
                .authenticate(usernamePasswordAuthenticationToken);
        UserEntity userEntity = (UserEntity) authentication.getPrincipal();
        String token = this.jwtService.generatedToken(this.userMapper.toDomain(userEntity));
        return ResponseEntity.ok(new UserResponseDTO(token));

    }

    @PostMapping("/register")
    public ResponseEntity<UserDTO> register(@RequestBody UserDTO userDTO) {

        try {
            User user = this.userMapper.toDomain(userDTO);
            this.authService.loadUserByUsername(user.getUsername());
            return ResponseEntity.badRequest().build();
        } catch (UsernameNotFoundException e) {

        }

        User userDomain = this.userMapper.toDomain(userDTO);
        this.userMapper.toDTO(this.userService.register(userDomain));
        return ResponseEntity.ok(userDTO);

    }

}
