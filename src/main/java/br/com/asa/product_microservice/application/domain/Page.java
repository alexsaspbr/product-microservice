package br.com.asa.product_microservice.application.domain;

import java.util.List;

public record Page<T>(List<T> content, Long totalItems, Integer totalPages, Integer pageNumber, Integer totalPageItems) {
}
