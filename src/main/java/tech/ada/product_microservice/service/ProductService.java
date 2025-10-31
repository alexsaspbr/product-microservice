package tech.ada.product_microservice.service;
import tech.ada.product_microservice.model.Product;

import java.math.BigDecimal;
import java.util.List;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import tech.ada.product_microservice.model.Product;
import tech.ada.product_microservice.repository.ProductRepository;

import java.awt.*;

@Service
@RequiredArgsConstructor

public class ProductService {

    private final ProductRepository productRepository;

    public ProductService(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }


    public List<Product> allProducts() {
        return this.productRepository.findAll();
    }

    public Product getProductByDescription(String description) {
        return this.productRepository.findByDescription(description)
                .orElseThrow(() -> new RuntimeException("Producto no encontrado con descripción: " + description));
    }

    public Product getProductBySKU(Long SKU) {
        return this.productRepository.findBySKU(SKU);
    }
    public Product create(Product product) {
        return this.productRepository.save(product);
    }

    public Product updateProduct(Long sku, Product product) {
        Product productBySKU = this.getProductBySKU(sku);
        if (productBySKU == null) {
            throw new RuntimeException("Produto nao encontrado som SKU " + sku);
        }
        product.setId(productBySKU.getId());
        product.setSKU(productBySKU.getSKU());
        return this.productRepository.save(product);
    }

    public List<Product> searchByDescriptionJPQL(String keyword) {
        if (keyword == null || keyword.trim().isEmpty()) {
            throw new IllegalArgumentException("El término de búsqueda no puede estar vacío");
        }
        return this.productRepository.searchByDescriptionJPQL(keyword);
    }

    public Product partialUpdate(Long sku, Product product) {
        Product productBySKU = this.getProductBySKU(sku);

        product.setId(product.getId());
        product.setSKU(product.getSKU());
        return this.productRepository.save(product);

    }


    public void deleteProduct(Long sku) {
        Product productBySKU = this.getProductBySKU(sku);
        if (productBySKU == null) {
            throw new RuntimeException("Produto nao encontrado com SKU " + sku);
        }
        this.productRepository.delete(productBySKU);
    }

    public List<Product> searchDescription(String keyword) {
        return this.productRepository.findByDescriptionContainingIgnoreCase(keyword);
    }

    public List<Product> findByPriceGreaterThan(BigDecimal price) {
        return this.productRepository.findByPriceGreaterThan(price);
    }

    public List<Product> findByPriceBetween(BigDecimal minPrice, BigDecimal maxPrice) {
        return this.productRepository.findByPriceBetween(minPrice, maxPrice);
    }

    public List<Product> findBySKUAndPriceLessThan(Long sku, BigDecimal maxPrice) {
        return this.productRepository.findBySKUAndPriceLessThan(sku, maxPrice);
    }

    public List<Product> findTop5ByOrderByPriceAsc() {
        return this.productRepository.findTop5ByOrderByPriceAsc();
    }

    public Long countProductsWithPriceGreaterThan(BigDecimal price) {
        return this.productRepository.countProductsWithPriceGreaterThan(price);
    }

    public List<Product> searchProducts(String description, BigDecimal minPrice, BigDecimal maxPrice) {
        return this.productRepository.searchProducts(description, minPrice, maxPrice);
    }


}