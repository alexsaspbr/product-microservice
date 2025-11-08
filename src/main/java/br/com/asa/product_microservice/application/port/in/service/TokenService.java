package br.com.asa.product_microservice.application.port.in.service;

import br.com.asa.product_microservice.application.domain.User;
import br.com.asa.product_microservice.application.domain.exception.TokenInvalidException;

public interface TokenService {

    String generatedToken(User user) throws TokenInvalidException;

    String validateToken(String token) throws TokenInvalidException;

}
