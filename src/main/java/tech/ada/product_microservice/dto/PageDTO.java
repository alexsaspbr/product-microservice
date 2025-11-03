package tech.ada.product_microservice.dto;

import java.util.List;

public record PageDTO<T>(List<T> content, Long totalItems, Integer totalPages, Integer pageNumber, Integer totalPageItems) {
}
