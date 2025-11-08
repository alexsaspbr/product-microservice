package br.com.asa.product_microservice.application.domain.exception;

public class ClientException extends BusinessException {
    public ClientException(String message) {
        super(message);
    }
}
