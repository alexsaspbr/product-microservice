package br.com.asa.product_microservice.infrastructure.mapper;

import org.springframework.stereotype.Component;
import br.com.asa.product_microservice.application.domain.Product;
import br.com.asa.product_microservice.infrastructure.dto.ProductDTO;

@Component
public final class ProductDTOMapper {

    public ProductDTO toDTO(Product product) {
        ProductDTO productDTO = new ProductDTO();
        productDTO.setSku(product.getSku());
        productDTO.setDescription(product.getDescription());
        productDTO.setPrice(product.getPrice());
        return productDTO;
    }

    public Product toDomain(ProductDTO productDTO) {
        Product product = new Product();
        product.setSku(productDTO.getSku());
        product.setDescription(productDTO.getDescription());
        product.setPrice(productDTO.getPrice());
        return product;
    }

}
