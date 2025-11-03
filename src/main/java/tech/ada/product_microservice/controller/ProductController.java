package tech.ada.product_microservice.controller;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.enums.SecuritySchemeType;
import io.swagger.v3.oas.annotations.info.Info;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.security.SecurityScheme;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import tech.ada.product_microservice.dto.PageDTO;
import tech.ada.product_microservice.dto.PageableDTO;
import tech.ada.product_microservice.dto.ProductDTO;
import tech.ada.product_microservice.service.ProductService;

import java.util.List;

@Tag(name = "Products")
@RestController
@RequestMapping("/products")
@SecurityScheme(
        name = "basicAuth", // can be set to anything
        type = SecuritySchemeType.HTTP,
        scheme = "basic"
)
@OpenAPIDefinition(
        info = @Info(title = "Product API", version = "v1"),
        security = @SecurityRequirement(name = "basicAuth") // references the name defined in the line 3
)
@RequiredArgsConstructor
public class ProductController {

    private final ProductService productService;

    @Operation
    @GetMapping
    public ResponseEntity<List<ProductDTO>> allProducts() {
        return ResponseEntity.ok(this.productService.allProducts());
    }

    @GetMapping("/paging")
    public ResponseEntity<PageDTO<ProductDTO>> allProducts(PageableDTO pageableDTO) {
        return ResponseEntity.ok(this.productService.allProducts(pageableDTO));
    }

    //GET BY ID
    @GetMapping("/{sku}")
    public ResponseEntity<ProductDTO> getProduct(@PathVariable Long sku) {
        //return ResponseEntity.ok(this.productService.getProductBySku(sku));
        return ResponseEntity.ok(this.productService.searchBySku(sku));
    }

    @GetMapping("/search-by-description")
    public ResponseEntity<List<ProductDTO>> getProduct(@RequestParam("description") String description) {
        return ResponseEntity.ok(this.productService.searchByDescription(description));
    }

    //POST - CREATE
    @PostMapping
    public ResponseEntity<ProductDTO> create(@Valid @RequestBody ProductDTO product) {
        return ResponseEntity.status(HttpStatus.CREATED).body(this.productService.create(product));
    }

    //PUT - UPDATE ALL
    @PutMapping("/{sku}")
    public ResponseEntity<ProductDTO> updateProduct(@PathVariable Long sku,
                                                    @RequestBody ProductDTO productDTO) {
        return ResponseEntity.ok(this.productService.updateProduct(sku, productDTO));
    }

    //PATCH - PARTIAL UPDATE
    @PatchMapping("/{sku}")
    public ResponseEntity<ProductDTO> updatePrice(@PathVariable Long sku,
                                                  @RequestBody ProductDTO productDTO) {
        return ResponseEntity.ok(this.productService.updatePrice(sku, productDTO));
    }

    //DELETE - REMOVE
    @DeleteMapping("/{sku}")
    public ResponseEntity<Void> deleteProduct(@PathVariable Long sku) {
        this.productService.deleteProduct(sku);
        return ResponseEntity.noContent().build();
    }

}
