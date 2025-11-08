package br.com.asa.product_microservice.application.port.in.service;

import br.com.asa.product_microservice.application.domain.User;

public interface UserService {

    User register(User user);

}
