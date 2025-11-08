package tech.ada.product_microservice.application.domain;

import java.math.BigDecimal;

public class Product {

    private Long sku;
    private String description;
    private BigDecimal price;

    public Product(){}

    public Product(String description, Long sku, BigDecimal price) {
        this.description = description;
        this.sku = sku;
        this.price = price;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    public Long getSku() {
        return sku;
    }

    public void setSku(Long sku) {
        this.sku = sku;
    }
}
