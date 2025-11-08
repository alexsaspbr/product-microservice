package br.com.asa.product_microservice.infrastructure.dto;

import java.util.List;

public record PageDTO<T>(List<T> content, Long totalItems, Integer totalPages, Integer pageNumber, Integer totalPageItems) {
}
