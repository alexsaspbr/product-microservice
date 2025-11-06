package tech.ada.product_microservice.service;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.http.ResponseEntity;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;
import tech.ada.product_microservice.client.ProductDummyClient;
import tech.ada.product_microservice.dto.PageDTO;
import tech.ada.product_microservice.dto.PageableDTO;
import tech.ada.product_microservice.dto.ProductDTO;
import tech.ada.product_microservice.dto.ProductDummyDTO;
import tech.ada.product_microservice.exception.BusinessException;
import tech.ada.product_microservice.exception.ClientException;
import tech.ada.product_microservice.mapper.ProductMapper;
import tech.ada.product_microservice.model.Product;
import tech.ada.product_microservice.repository.ProductRepository;
import tech.ada.product_microservice.util.SortUtils;

import java.math.BigDecimal;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

@Slf4j
@Service
@RequiredArgsConstructor
public class ProductService {

    private final ProductRepository productRepository;
    private final ProductDummyClient productDummyClient;
    private final ProductMapper productMapper;
    private PageableDTO pageableDTO;

    @Cacheable("products")
    public List<ProductDTO> allProducts() {
        return this.productRepository.findAll()
                .stream().map(this.productMapper::toDTO).toList();
    }

    public PageDTO<ProductDTO> allProducts(PageableDTO pageableDTO) {
        Optional<Sort> optionalSort = SortUtils.createSort(pageableDTO.getSort());
        PageRequest pageRequest = optionalSort.isPresent() ? PageRequest.of(pageableDTO.getPage(),
                                                pageableDTO.getSize(),
                                                optionalSort.get()) : PageRequest.of(pageableDTO.getPage(),
                                                pageableDTO.getSize());
        Page<Product> pageProducts = this.productRepository.findAll(pageRequest);
        return new PageDTO<ProductDTO>(pageProducts.getContent().stream()
                .map(this.productMapper::toDTO).toList(),
                pageProducts.getTotalElements(),
                pageProducts.getTotalPages(),
                pageProducts.getNumber(),
                pageProducts.getNumberOfElements());
    }

    public ProductDTO getProductBySku(Long sku) {
        return this.productMapper.toDTO(this.getBySku(sku));
    }

    private Product getBySku(Long sku) {
        return this.productRepository.findBySku(sku)
                .orElseThrow(() -> new RuntimeException("Produto nao encontrado"));
    }

    @Transactional
    @CachePut("products")
    public ProductDTO create(ProductDTO productDTO) {

        try {
            final ResponseEntity<ProductDummyDTO> response = productDummyClient.getProductById(productDTO.getSku());
            if(response.getStatusCode().is2xxSuccessful()
                    && Objects.nonNull(response.getBody())){
                productDTO.setPrice(response.getBody().getPrice());
                productDTO.setDescription(response.getBody().getTitle());
            }
            Product product = this.productMapper.toEntity(productDTO);
            return this.productMapper.toDTO(this.productRepository.save(product));
        } catch (Exception e) {
            throw new ClientException(e.getMessage());
        }
    }

    @CachePut("products")
    public ProductDTO updatePrice(Long sku, ProductDTO produtoDTO) {
        Product productBySku = this.getBySku(sku);
        BigDecimal price = produtoDTO.getPrice();
        this.productRepository.updateProduct(productBySku.getId(), price);
        productBySku.setPrice(price);
        return this.productMapper.toDTO(productBySku);
    }

    public ProductDTO updateProduct(Long sku, ProductDTO productDTO) {
        Product productBySku = this.getBySku(sku);

        Product product = new Product();
        product.setId(productBySku.getId());
        product.setSku(productBySku.getSku());
        product.setDescription(productBySku.getDescription());
        product.setPrice(productBySku.getPrice());
        Product productUpdated = this.productRepository.save(product);
        return this.productMapper.toDTO(productUpdated);
    }

    @CacheEvict(value = "products", allEntries = true)
    public void deleteProduct(Long sku) {
        Product productBySku = this.getBySku(sku);

        this.productRepository.deleteById(productBySku.getId());
    }

    public List<ProductDTO> searchByDescription(String description) {
        return this.productRepository.searchByDescription(description)
                .stream().map(this.productMapper::toDTO).toList();
    }

    public ProductDTO searchBySku(Long sku) {
        return this.productRepository.searchBySku(sku).stream().findFirst()
                .map(this.productMapper::toDTO)
                .orElse(null);
    }

    @Scheduled(fixedRate = 10000)
    @CacheEvict(value = "products", allEntries = true)
    public void clearCache() {
        log.info("Clear cache");
    }

}
