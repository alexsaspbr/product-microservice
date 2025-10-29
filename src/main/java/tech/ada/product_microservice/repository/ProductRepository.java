package tech.ada.product_microservice.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.web.bind.annotation.RequestParam;
import tech.ada.product_microservice.model.Product;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;

@Repository
public interface ProductRepository extends JpaRepository<Product, Long> {


    Product findBySKU(Long SKU);

    Optional<Product> findByDescription(String description);

    List<Product> findByDescriptionContainingIgnoreCase(String keyword);

    List<Product> findByPriceGreaterThan(BigDecimal price);

    List<Product> findByPriceBetween(BigDecimal minPrice, BigDecimal maxPrice);



    List<Product> findBySKUAndPriceLessThan(Long SKU, BigDecimal price);

    List<Product> findTop5ByOrderByPriceAsc();

    @Query("SELECT p FROM Product p WHERE LOWER(p.description) LIKE LOWER(CONCAT('%', :keyword, '%'))")
    List<Product> searchByDescriptionJPQL(@Param("keyword") String keyword);

    @Query("SELECT COUNT(p) FROM Product p WHERE p.price > :price")
    Long countProductsWithPriceGreaterThan(@Param("price") BigDecimal price);

    @Query("""
    SELECT p FROM Product p
    WHERE (:description IS NULL OR LOWER(p.description) LIKE LOWER(CONCAT('%', :description, '%')))
    AND (:minPrice IS NULL OR p.price >= :minPrice)
    AND (:maxPrice IS NULL OR p.price <= :maxPrice)
    """)
    List<Product> searchProducts(
            @Param("description") String description,
            @Param("minPrice") BigDecimal minPrice,
            @Param("maxPrice") BigDecimal maxPrice);



}
