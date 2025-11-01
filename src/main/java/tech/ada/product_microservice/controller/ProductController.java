package tech.ada.product_microservice.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import tech.ada.product_microservice.model.Product;
import tech.ada.product_microservice.service.ProductService;

import java.util.List;

@RestController
@RequestMapping("/products")
@RequiredArgsConstructor
public class ProductController {

    private final ProductService productService;

    @GetMapping
    public ResponseEntity<List<Product>> allProducts() {
        return ResponseEntity.ok(this.productService.allProducts());
    }

    @GetMapping("/{sku}")
    public ResponseEntity<Product> getProduct(@PathVariable Long sku) {
        return ResponseEntity.ok(this.productService.getProductBySku(sku));
    }

    @PostMapping
    public ResponseEntity<Product> create(@RequestBody Product product) {
        return ResponseEntity.status(HttpStatus.CREATED).body(this.productService.create(product));
    }

    @PutMapping("/{sku}")
    public ResponseEntity<Product> updateProduct(@PathVariable Long sku, @RequestBody Product product) {
        return ResponseEntity.ok(this.productService.updateProduct(sku, product));
    }

    @PatchMapping("/{sku}")
    public ResponseEntity<Product> partialUpdate(@PathVariable Long sku, @RequestBody Product product) {
        return ResponseEntity.ok(this.productService.partialUpdate(sku, product));
    }

    @DeleteMapping("/{sku}")
    public ResponseEntity<Void> deleteProduct(@PathVariable Long sku) {
        this.productService.deleteProduct(sku);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/search")
    public ResponseEntity<List<Product>> searchProducts(
            @RequestParam(required = false) String description,
            @RequestParam(required = false) Double minPrice,
            @RequestParam(required = false) Double maxPrice) {

        List<Product> results = productService.searchProducts(description, minPrice, maxPrice);
        return ResponseEntity.ok(results);
    }
}
