package tech.ada.product_microservice.application.port.in.service;

import tech.ada.product_microservice.application.domain.User;

public interface UserService {

    User register(User user);

}
