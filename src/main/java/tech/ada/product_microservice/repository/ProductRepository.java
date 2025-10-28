package tech.ada.product_microservice.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import tech.ada.product_microservice.model.Product;

import java.math.BigDecimal;
import java.util.List;

@Repository
public interface ProductRepository extends JpaRepository<Product, Long> {

    Product findBySku(Long sku);
    Product findByDescriptionContainingAndPrice(String description, BigDecimal price);

    @Query(value = "SELECT * FROM tb_products where sku = :sku", nativeQuery = true)
    Product superQuery(@Param("sku") Long sku);

    @Query(value = "SELECT p FROM Product p WHERE p.sku = :sku")
    Product superQuery2(@Param("sku") Long sku);

    @Query(value = "SELECT p FROM Product p WHERE p.description LIKE %:description% AND p.price BETWEEN :minPrice AND :maxPrice")
    List<Product> findAllByDescriptionContainingAndPriceBetween(@Param("description") String description, @Param("minPrice") BigDecimal minPrice, @Param("maxPrice") BigDecimal maxPrice);

}
