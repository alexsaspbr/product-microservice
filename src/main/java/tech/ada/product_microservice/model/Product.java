package tech.ada.product_microservice.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;

@Entity
@Table(name = "tb_products")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@NamedQueries({
        @NamedQuery(
                name = "Product.searchByDescription",
                query = "SELECT p FROM Product p WHERE p.description LIKE :description"
        )
})
@NamedNativeQuery(
        name = "Product.searchBySku",
        query = "SELECT * FROM TB_PRODUCTS p WHERE p.sku = :sku",
        resultClass = Product.class
)
public class Product {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, unique = true)
    private Long sku;

    @Column(length = 100)
    private String description;

    @Column(precision = 16, scale = 2)
    private BigDecimal price;


}
