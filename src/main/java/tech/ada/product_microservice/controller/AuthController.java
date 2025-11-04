package tech.ada.product_microservice.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import tech.ada.product_microservice.dto.UserDTO;
import tech.ada.product_microservice.dto.UserResponseDTO;
import tech.ada.product_microservice.exception.TokenInvalidException;
import tech.ada.product_microservice.service.UserService;

import java.util.Objects;

@RestController
@RequestMapping("/auth")
@RequiredArgsConstructor
public class AuthController {

    private final UserService userService;
    private final AuthenticationManager authenticationManager;

    @PostMapping("/login")
    public ResponseEntity login(@RequestBody UserDTO userDTO) throws
            TokenInvalidException {

        var usernamePasswordAuthenticationToken = new
                UsernamePasswordAuthenticationToken(
                userDTO.getUsername(),
                userDTO.getPassword());
        var authentication = this.authenticationManager
                .authenticate(usernamePasswordAuthenticationToken);
        //TODO - Implementar geracao do token
        String token = "";

        return ResponseEntity.ok(new UserResponseDTO(token));

    }

    @PostMapping("/register")
    public ResponseEntity register(@RequestBody UserDTO userDTO) {


        if(Objects.nonNull(this.userService.loadUserByUsername(userDTO.getUsername()))){
            return ResponseEntity.badRequest().build();
        }

        this.userService.register(userDTO);
        return ResponseEntity.ok("Usuario registrado");

    }

}
