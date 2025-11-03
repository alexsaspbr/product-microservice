package tech.ada.product_microservice.dto;

import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class PageableDTO {

    private Integer size;
    private Integer page;
    private String sort;

}
