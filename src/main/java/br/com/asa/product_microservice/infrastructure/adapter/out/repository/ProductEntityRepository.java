package br.com.asa.product_microservice.infrastructure.adapter.out.repository;

import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import br.com.asa.product_microservice.infrastructure.adapter.out.repository.entity.ProductEntity;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;

@Repository
public interface ProductEntityRepository extends JpaRepository<ProductEntity, Long> {

    Optional<ProductEntity> findBySku(Long sku);
    ProductEntity findByDescriptionContainingAndPrice(String description, BigDecimal price);

    @Query(value = "SELECT * FROM tb_products where sku = :sku", nativeQuery = true)
    ProductEntity superQuery(@Param("sku") Long sku);

    @Query(value = "SELECT p FROM ProductEntity p WHERE p.sku = :sku")
    ProductEntity superQuery2(@Param("sku") Long sku);

    //DELETE
    @Modifying
    @Transactional
    @Query(value = "DELETE FROM TB_PRODUCTS p WHERE p.id = :id", nativeQuery = true)
    void deleteById(@Param("id") Long id);

    //UPDATE
    @Modifying
    @Transactional
    @Query(value = "UPDATE TB_PRODUCTS p set p.price = :price WHERE p.id = :id", nativeQuery = true)
    void updateProduct(@Param("id") Long id, @Param("price") BigDecimal price);

    List<ProductEntity> searchByDescription(@Param("description") String description);

    List<ProductEntity> searchBySku(@Param("sku") Long sku);

}
