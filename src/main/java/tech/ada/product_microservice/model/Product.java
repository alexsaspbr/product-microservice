package tech.ada.product_microservice.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonTypeId;
import jakarta.persistence.*;
import lombok.*;
import org.springframework.boot.autoconfigure.domain.EntityScan;

import java.math.BigDecimal;

@Entity
@Table(name = "tb_products")
@Data
//@AllArgsConstructor
//@NoArgsConstructor
//@Getter
//@Setter
public class Product {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private  Long id;

    @Column(nullable = false, unique = true)
    @JsonProperty("sku")
    private Long SKU;

    @Column(length = 100)
    private String description;

    @Column(precision = 16, scale = 2)
    private BigDecimal price;

    public Product(long SKU, String description) {
    }

    public Product(){}

    public void setId(Long id) {
        this.id = id;
    }

    public void setSKU(Long SKU) {
        this.SKU = SKU;
    }

    public Long getId() {
        return id;
    }

    public Long getSKU() {
        return SKU;
    }
}
