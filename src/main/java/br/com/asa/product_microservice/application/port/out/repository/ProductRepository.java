package br.com.asa.product_microservice.application.port.out.repository;

import br.com.asa.product_microservice.application.domain.Page;
import br.com.asa.product_microservice.application.domain.Pageable;
import br.com.asa.product_microservice.application.domain.Product;

import java.util.List;

public interface ProductRepository {

    List<Product> findAll();
    Page<Product> findAll(Pageable pageable);
    Product findBySku(Long sku);
    Product save(Product productDTO);
    Product updatePrice(Long sku, Product product);
    Product updateProduct(Long sku, Product product);
    void deleteBySku(Long sku);
    List<Product> searchByDescription(String description);
    Product searchBySku(Long sku);

}
