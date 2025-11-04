package tech.ada.product_microservice.dto;

import lombok.Data;
import lombok.RequiredArgsConstructor;

@Data
@RequiredArgsConstructor
public class UserResponseDTO {

    private final String token;

}
