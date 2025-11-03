package tech.ada.product_microservice.model;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public enum UserRole {

    ADMIN("ADMIN_ROLE"), USER("USER_ROLE");
    private final String role;

}
