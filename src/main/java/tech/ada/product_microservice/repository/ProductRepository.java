package tech.ada.product_microservice.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import tech.ada.product_microservice.model.Product;

import java.util.List;

@Repository
public interface ProductRepository extends JpaRepository<Product, Long> {

    Product findBySku(Long sku);

    @Query("SELECT p FROM Product p " +
            "WHERE (:description IS NULL OR LOWER(p.description) LIKE LOWER(CONCAT('%', :description, '%'))) " +
            "AND (:minPrice IS NULL OR p.price >= :minPrice) " +
            "AND (:maxPrice IS NULL OR p.price <= :maxPrice)")
    List<Product> searchProducts(
            @Param("description") String description,
            @Param("minPrice") Double minPrice,
            @Param("maxPrice") Double maxPrice
    );
}
