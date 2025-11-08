package br.com.asa.product_microservice.infrastructure.adapter.out.repository;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Component;
import br.com.asa.product_microservice.application.domain.Page;
import br.com.asa.product_microservice.application.domain.Pageable;
import br.com.asa.product_microservice.application.domain.Product;
import br.com.asa.product_microservice.application.port.out.repository.ProductRepository;
import br.com.asa.product_microservice.infrastructure.adapter.out.repository.entity.ProductEntity;
import br.com.asa.product_microservice.infrastructure.mapper.ProductEntityMapper;
import br.com.asa.product_microservice.infrastructure.util.SortUtils;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;

@Component
@RequiredArgsConstructor
public class ProductRepositoryImpl implements ProductRepository {

    private final ProductEntityRepository productEntityRepository;
    private final ProductEntityMapper productEntityMapper;

    @Override
    public List<Product> findAll() {
        return this.productEntityRepository.findAll()
                .stream().map(productEntityMapper::toDomain).toList();
    }

    @Override
    public Page<Product> findAll(Pageable pageable) {
        Optional<Sort> optionalSort = SortUtils.createSort(pageable.getSort());
        PageRequest pageRequest = optionalSort.isPresent() ? PageRequest.of(pageable.getPage(),
                pageable.getSize(),
                optionalSort.get()) : PageRequest.of(pageable.getPage(),
                pageable.getSize());
        org.springframework.data.domain.Page<ProductEntity> pageProducts = this.productEntityRepository.findAll(pageRequest);
        return new Page<>(pageProducts.getContent().stream()
                .map(this.productEntityMapper::toDomain).toList(),
                pageProducts.getTotalElements(),
                pageProducts.getTotalPages(),
                pageProducts.getNumber(),
                pageProducts.getNumberOfElements());
    }

    @Override
    public Product findBySku(Long sku) {
        return this.productEntityMapper.toDomain(this.getBySku(sku));
    }

    @Transactional
    @Override
    public Product save(Product product) {
        ProductEntity productEntity = this.productEntityMapper.toEntity(product);
        return this.productEntityMapper.toDomain(this.productEntityRepository.save(productEntity));
    }

    @Override
    public Product updatePrice(Long sku, Product product) {
        ProductEntity productEntityBySku = this.getBySku(sku);
        BigDecimal price = product.getPrice();
        this.productEntityRepository.updateProduct(productEntityBySku.getId(), price);
        productEntityBySku.setPrice(price);
        return this.productEntityMapper.toDomain(productEntityBySku);
    }

    @Override
    public Product updateProduct(Long sku, Product product) {
        ProductEntity productEntityBySku = this.getBySku(sku);
        ProductEntity productEntity = new ProductEntity();
        productEntity.setId(productEntityBySku.getId());
        productEntity.setSku(productEntityBySku.getSku());
        productEntity.setDescription(productEntityBySku.getDescription());
        productEntity.setPrice(productEntityBySku.getPrice());
        ProductEntity productEntityUpdated = this.productEntityRepository.save(productEntity);
        return this.productEntityMapper.toDomain(productEntityUpdated);
    }

    @Override
    public void deleteBySku(Long sku) {
        ProductEntity productEntityBySku = this.getBySku(sku);
        this.productEntityRepository.deleteById(productEntityBySku.getId());
    }

    @Override
    public List<Product> searchByDescription(String description) {
        return this.productEntityRepository.searchByDescription(description)
                .stream().map(this.productEntityMapper::toDomain).toList();
    }

    @Override
    public Product searchBySku(Long sku) {
        return this.productEntityRepository.searchBySku(sku).stream().findFirst()
                .map(this.productEntityMapper::toDomain)
                .orElse(null);
    }

    private ProductEntity getBySku(Long sku) {
        return this.productEntityRepository.findBySku(sku)
                .orElseThrow(() -> new RuntimeException("Product not found"));
    }

}
