package tech.ada.product_microservice.controller;

import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import tech.ada.product_microservice.dto.UserDTO;
import tech.ada.product_microservice.dto.UserResponseDTO;
import tech.ada.product_microservice.exception.TokenInvalidException;
import tech.ada.product_microservice.model.User;
import tech.ada.product_microservice.service.AuthService;
import tech.ada.product_microservice.service.JWTService;
import tech.ada.product_microservice.service.UserService;

@Slf4j
@RestController
@RequestMapping("/auth")
@Tag(name = "Authentication")
@RequiredArgsConstructor
public class AuthController {

    private final UserService userService;
    private final AuthService authService;
    private final JWTService jwtService;
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

        String token = this.jwtService.generatedToken((User) authentication.getPrincipal());
        return ResponseEntity.ok(new UserResponseDTO(token));

    }

    @PostMapping("/register")
    public ResponseEntity<UserDTO> register(@RequestBody UserDTO userDTO) {

        try {
            this.authService.loadUserByUsername(userDTO.getUsername());
            return ResponseEntity.badRequest().build();
        } catch (UsernameNotFoundException e) {
            
        }

        this.userService.register(userDTO);
        return ResponseEntity.ok(userDTO);

    }

}
