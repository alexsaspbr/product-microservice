package tech.ada.product_microservice.infrastructure.adapter.in.web.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import tech.ada.product_microservice.application.domain.Page;
import tech.ada.product_microservice.application.domain.Pageable;
import tech.ada.product_microservice.application.domain.Product;
import tech.ada.product_microservice.application.port.in.service.ProductService;
import tech.ada.product_microservice.infrastructure.dto.PageDTO;
import tech.ada.product_microservice.infrastructure.dto.PageableDTO;
import tech.ada.product_microservice.infrastructure.dto.ProductDTO;
import tech.ada.product_microservice.infrastructure.mapper.PageDTOMapper;
import tech.ada.product_microservice.infrastructure.mapper.ProductDTOMapper;

import java.util.List;

@Tag(name = "Products")
@RestController
@RequestMapping("/products")
@RequiredArgsConstructor
@SecurityRequirement(name = "Bearer Authentication")
public class ProductController {

    private final ProductService productService;
    private final ProductDTOMapper productDTOMapper;
    private final PageDTOMapper pageDTOMapper;

    @Operation
    @GetMapping
    public ResponseEntity<List<ProductDTO>> allProducts() {
        return ResponseEntity.ok(this.productService
                .allProducts().stream().map(productDTOMapper::toDTO).toList());
    }

    @GetMapping("/paging")
    public ResponseEntity<PageDTO<ProductDTO>> allProducts(PageableDTO pageableDTO) {
        Pageable pageable = this.pageDTOMapper.toDomain(pageableDTO);
        Page<Product> productPage = this.productService.allProducts(pageable);
        return ResponseEntity.ok(this.pageDTOMapper.toDTO(productPage));
    }

    //GET BY ID
    @GetMapping("/{sku}")
    public ResponseEntity<ProductDTO> getProduct(@PathVariable Long sku) {
        return ResponseEntity.ok(this.productDTOMapper.toDTO(this.productService.searchBySku(sku)));
    }

    @GetMapping("/search-by-description")
    public ResponseEntity<List<ProductDTO>> getProduct(@RequestParam("description") String description) {
        return ResponseEntity.ok(this.productService.searchByDescription(description)
                .stream().map(this.productDTOMapper::toDTO).toList());
    }

    //POST - CREATE
    @PostMapping
    public ResponseEntity<ProductDTO> create(@Valid @RequestBody ProductDTO productDTO) {
        Product product = this.productDTOMapper.toDomain(productDTO);
        return ResponseEntity.status(HttpStatus.CREATED).body(this.productDTOMapper.toDTO(this.productService.create(product)));
    }

    //PUT - UPDATE ALL
    @PutMapping("/{sku}")
    public ResponseEntity<ProductDTO> updateProduct(@PathVariable Long sku,
                                                    @RequestBody ProductDTO productDTO) {
        Product product = this.productDTOMapper.toDomain(productDTO);
        this.productDTOMapper.toDTO(this.productService.updateProduct(sku, product));
        return ResponseEntity.ok(this.productDTOMapper.toDTO(this.productService.updateProduct(sku, product)));
    }

    //PATCH - PARTIAL UPDATE
    @PatchMapping("/{sku}")
    public ResponseEntity<ProductDTO> updatePrice(@PathVariable Long sku,
                                                  @RequestBody ProductDTO productDTO) {
        Product product = this.productDTOMapper.toDomain(productDTO);
        return ResponseEntity.ok(this.productDTOMapper.toDTO(this.productService.updatePrice(sku, product)));
    }

    //DELETE - REMOVE
    @DeleteMapping("/{sku}")
    public ResponseEntity<Void> deleteProduct(@PathVariable Long sku) {
        this.productService.deleteProduct(sku);
        return ResponseEntity.noContent().build();
    }

}
