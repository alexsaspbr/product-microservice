package br.com.asa.product_microservice.infrastructure.adapter.out.service.impl;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.http.ResponseEntity;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;
import br.com.asa.product_microservice.application.domain.Page;
import br.com.asa.product_microservice.application.domain.Pageable;
import br.com.asa.product_microservice.application.domain.Product;
import br.com.asa.product_microservice.application.domain.exception.ClientException;
import br.com.asa.product_microservice.application.port.in.service.ProductService;
import br.com.asa.product_microservice.application.port.out.repository.ProductRepository;
import br.com.asa.product_microservice.infrastructure.adapter.out.web.client.ProductDummyClient;
import br.com.asa.product_microservice.infrastructure.adapter.out.repository.entity.ProductEntity;
import br.com.asa.product_microservice.infrastructure.dto.ProductDummyDTO;
import br.com.asa.product_microservice.infrastructure.mapper.ProductEntityMapper;

import java.util.List;
import java.util.Objects;

@Slf4j
@Service
@RequiredArgsConstructor
public class ProductServiceImpl implements ProductService {

    private final ProductRepository productRepository;
    private final ProductDummyClient productDummyClient;
    private final ProductEntityMapper productEntityMapper;

    @Cacheable("products")
    public List<Product> allProducts() {
        return this.productRepository.findAll();
    }

    public Page<Product> allProducts(Pageable pageable) {
        return this.productRepository.findAll(pageable);
    }

    public Product getProductBySku(Long sku) {
        return this.productRepository.findBySku(sku);
    }

    @CachePut("products")
    public Product create(Product product) {

        try {
            final ResponseEntity<ProductDummyDTO> response = productDummyClient.getProductById(product.getSku());
            if(response.getStatusCode().is2xxSuccessful()
                    && Objects.nonNull(response.getBody())){
                product.setPrice(response.getBody().getPrice());
                product.setDescription(response.getBody().getTitle());
            }
            ProductEntity productEntity = this.productEntityMapper.toEntity(product);
            return this.productRepository.save(product);
        } catch (Exception e) {
            throw new ClientException(e.getMessage());
        }
    }

    @CachePut("products")
    public Product updatePrice(Long sku, Product product) {
        return this.productRepository.updatePrice(sku, product);
    }

    public Product updateProduct(Long sku, Product product) {
       return this.productRepository.updateProduct(sku, product);
    }

    @CacheEvict(value = "products", allEntries = true)
    public void deleteProduct(Long sku) {
        this.productRepository.deleteBySku(sku);
    }

    public List<Product> searchByDescription(String description) {
        return this.productRepository.searchByDescription(description);
    }

    public Product searchBySku(Long sku) {
        return this.productRepository.searchBySku(sku);
    }

    @Scheduled(fixedRate = 10000)
    @CacheEvict(value = "products", allEntries = true)
    public void clearCache() {
        log.info("Clear cache");
    }

}
