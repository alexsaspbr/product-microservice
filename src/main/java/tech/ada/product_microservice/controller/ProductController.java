package tech.ada.product_microservice.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.data.repository.query.Param;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import tech.ada.product_microservice.model.Product;
import tech.ada.product_microservice.service.ProductService;

import java.math.BigDecimal;
import java.util.List;

@RestController
@RequestMapping("/products")
@RequiredArgsConstructor
public class ProductController {

    private final ProductService productService;

    public ProductController(ProductService productService) {
        this.productService = productService;
    }


    @GetMapping
    public ResponseEntity<List<Product>> allProducts() {
        return ResponseEntity.ok(this.productService.allProducts());
    }

    @GetMapping("/{SKU}")
    public ResponseEntity<Product> getProduct(@PathVariable Long SKU) {
        return ResponseEntity.ok(this.productService.getProductBySKU(SKU));
    }

    @GetMapping("/description/{description}")
    public ResponseEntity<Product> getProductByDescription(@PathVariable String description) {
        return ResponseEntity.ok(this.productService.getProductByDescription(description));
    }

//    @GetMapping("/search-jpql")
//    public ResponseEntity<List<Product>> searchProductsByDescription(@RequestParam String keyword) {
//        return ResponseEntity.ok(this.productService.searchByDescriptionJPQL(keyword));
//    }

    @PostMapping
    public ResponseEntity<Product> createProduct(@RequestBody Product product){
        return ResponseEntity.status(HttpStatus.CREATED).body(this.productService.create(product));
    }

    @PutMapping("/{sku}")
    public ResponseEntity<Product> updateProduct(@PathVariable Long sku, @RequestBody Product product) {
        return ResponseEntity.ok(this.productService.updateProduct(sku, product));
    }

    @PatchMapping("/{sku}")
    public ResponseEntity<Product> partialUpdate(@PathVariable Long sku, @RequestBody Product product) {
        return ResponseEntity.ok((this.productService.partialUpdate(sku, product)));
    }

    @DeleteMapping("/{sku}")
    public ResponseEntity<Void> deleteProduct(@PathVariable Long sku){
        this.productService.deleteProduct(sku);
        return ResponseEntity.noContent().build();
    }
    @GetMapping("/search")
    public ResponseEntity<List<Product>> searchDescription(@RequestParam String keyword) {
        return ResponseEntity.ok(this.productService.searchDescription(keyword));
    }
    //Buscar productos con precio mayor que un valor.
    @GetMapping("/search-by-price")
    public ResponseEntity<List<Product>> searchByPrice(@RequestParam ("minPrice")BigDecimal minPrice) {
        return ResponseEntity.ok(this.productService.findByPriceGreaterThan(minPrice));
    }
    //Buscar productos con precio entre dos valores
    @GetMapping("/search-by-price-range")
    public  ResponseEntity<List<Product>> searchByPriceBetween(@RequestParam("minPrice")BigDecimal minPrice,
                                                               @RequestParam("maxPrice") BigDecimal maxPrice) {
        List<Product> results = this.productService.findByPriceBetween(minPrice, maxPrice);
        return ResponseEntity.ok(results);
    }

    //Buscar productos por SKU y precio menor que un valor.
    @GetMapping("/search-by-sku-and-price")
    public ResponseEntity<List<Product>> findBySKUAndPriceLessThan(
            @RequestParam("sku") Long sku,
            @RequestParam("maxPrice") BigDecimal maxPrice) {
        List<Product> results = this.productService.findBySKUAndPriceLessThan(sku, maxPrice);
        return ResponseEntity.ok(results);
    }

    //Buscar los 5 productos más baratos.
    @GetMapping("/top-5")
    public ResponseEntity<List<Product>> getTopByPrice() {
        List<Product> results = this.productService.findTop5ByOrderByPriceAsc();
        return ResponseEntity.ok(results);
    }


    //Buscar productos cuya descripción contenga parte de um
    //texto (ignorando maiúsculas/minúsculas).
@GetMapping("/search-jpql")
public ResponseEntity<List<Product>> searchProductsByDescription(@RequestParam String keyword) {
    return ResponseEntity.ok(this.productService.searchByDescriptionJPQL(keyword));
}

// Contar productos con precio mayor que un valor (agregación JPQL)
    @GetMapping("/count-by-price")
    public ResponseEntity<Long> countProductsWithPriceGreaterThan(@Param("price") BigDecimal price) {
        Long count = this.productService.countProductsWithPriceGreaterThan(price);
        return ResponseEntity.ok(count);
    }

    //  Búsqueda dinámica con filtros opcionales
    @GetMapping("/dynamic-search")
    public ResponseEntity<List<Product>> searchProducts(
            @RequestParam(required = false) String description,
            @RequestParam(required = false) BigDecimal minPrice,
            @RequestParam(required = false) BigDecimal maxPrice) {
        List<Product> results = this.productService.searchProducts(description, minPrice, maxPrice);
        return ResponseEntity.ok(results);
    }







}
