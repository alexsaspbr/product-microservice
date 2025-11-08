package br.com.asa.product_microservice.infrastructure.mapper;

import org.springframework.stereotype.Component;
import br.com.asa.product_microservice.application.domain.Product;
import br.com.asa.product_microservice.infrastructure.adapter.out.repository.entity.ProductEntity;

@Component
public class ProductEntityMapper {

    public ProductEntity toEntity(Product product) {
        ProductEntity productEntity = new ProductEntity();
        productEntity.setSku(product.getSku());
        productEntity.setDescription(product.getDescription());
        productEntity.setPrice(product.getPrice());
        return productEntity;
    }

    public Product toDomain(ProductEntity productEntity) {
        Product product = new Product();
        product.setSku(productEntity.getSku());
        product.setDescription(productEntity.getDescription());
        product.setPrice(productEntity.getPrice());
        return product;
    }

}
