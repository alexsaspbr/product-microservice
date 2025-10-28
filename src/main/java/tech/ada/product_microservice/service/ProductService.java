package tech.ada.product_microservice.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import tech.ada.product_microservice.model.Product;
import tech.ada.product_microservice.repository.ProductRepository;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ProductService {

    private final ProductRepository productRepository;

    public List<Product> allProducts() {
        return this.productRepository.findAll();
    }

    public Product getProductBySku(Long sku) {
        return this.productRepository.findBySku(sku);
    }

    public Product create(Product product) {
        return this.productRepository.save(product);
    }

    public Product partialUpdate(Long sku, Product product) {
        Product productBySku = this.getProductBySku(sku);

        product.setId(productBySku.getId());
        product.setSku(productBySku.getSku());
        return this.productRepository.save(product);
    }

    public Product updateProduct(Long sku, Product product) {
        Product productBySku = this.getProductBySku(sku);
        if (productBySku == null) {
            throw new RuntimeException("Produto nao encontrado com SKU: " + sku);
        }

        product.setId(productBySku.getId());
        product.setSku(productBySku.getSku());
        return this.productRepository.save(product);
    }

    public void deleteProduct(Long sku) {
        Product productBySku = this.getProductBySku(sku);
        if (productBySku == null) {
            throw new RuntimeException("Produto nao encontrado com SKU: " + sku);
        }

        this.productRepository.delete(productBySku);
    }

    public void delete(Long sku) {
        Product productBySku = this.getProductBySku(sku);
        this.allProducts().remove(productBySku);
    }
}