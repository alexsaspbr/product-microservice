package tech.ada.product_microservice.model;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum UserRole {

    ADMIN("ADMIN_ROLE"), USER("USER_ROLE");
    private String role;

}
