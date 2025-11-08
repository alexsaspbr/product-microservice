package tech.ada.product_microservice.application.port.in.service;

import tech.ada.product_microservice.application.domain.Page;
import tech.ada.product_microservice.application.domain.Pageable;
import tech.ada.product_microservice.application.domain.Product;

import java.util.List;

public interface ProductService {

    List<Product> allProducts();
    Page<Product> allProducts(Pageable pageable);
    Product getProductBySku(Long sku);
    Product create(Product productDTO);
    Product updatePrice(Long sku, Product product);
    Product updateProduct(Long sku, Product product);
    void deleteProduct(Long sku);
    List<Product> searchByDescription(String description);
    Product searchBySku(Long sku);
}
