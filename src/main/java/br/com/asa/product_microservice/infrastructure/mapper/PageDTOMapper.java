package br.com.asa.product_microservice.infrastructure.mapper;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import br.com.asa.product_microservice.application.domain.Page;
import br.com.asa.product_microservice.application.domain.Pageable;
import br.com.asa.product_microservice.application.domain.Product;
import br.com.asa.product_microservice.infrastructure.dto.PageDTO;
import br.com.asa.product_microservice.infrastructure.dto.PageableDTO;
import br.com.asa.product_microservice.infrastructure.dto.ProductDTO;

@Component
@RequiredArgsConstructor
public class PageDTOMapper {

    private final ProductDTOMapper productDTOMapper;

    public PageDTO<ProductDTO> toDTO(Page<Product> page) {
        return new PageDTO<ProductDTO>(page.content().stream().map(this.productDTOMapper::toDTO).toList(),
                page.totalItems(),
                page.totalPages(),
                page.pageNumber(),
                page.totalPageItems());
    }

    public Pageable toDomain(PageableDTO pageableDTO) {
        return new Pageable(pageableDTO.getSize(), pageableDTO.getPage(), pageableDTO.getSort());
    }

}
