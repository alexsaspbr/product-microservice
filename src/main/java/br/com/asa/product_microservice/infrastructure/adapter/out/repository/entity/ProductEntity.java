package br.com.asa.product_microservice.infrastructure.adapter.out.repository.entity;

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
                name = "ProductEntity.searchByDescription",
                query = "SELECT p FROM ProductEntity p WHERE p.description LIKE :description"
        )
})
@NamedNativeQuery(
        name = "ProductEntity.searchBySku",
        query = "SELECT * FROM TB_PRODUCTS p WHERE p.sku = :sku",
        resultClass = ProductEntity.class
)
public class ProductEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, unique = true)
    private Long sku;

    @Column(length = 255)
    private String description;

    @Column(precision = 16, scale = 2)
    private BigDecimal price;


}
