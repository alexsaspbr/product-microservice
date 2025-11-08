package tech.ada.product_microservice.application.port.out.repository;

import org.springframework.security.core.userdetails.UserDetails;
import tech.ada.product_microservice.application.domain.User;

public interface UserRepository {

    User save(User user);

    UserDetails findByUsername(String username);

}
