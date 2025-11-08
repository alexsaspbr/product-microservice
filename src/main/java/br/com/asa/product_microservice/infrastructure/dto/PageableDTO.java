package br.com.asa.product_microservice.infrastructure.dto;

import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class PageableDTO {

    private Integer size;
    private Integer page;
    private String sort;

}
