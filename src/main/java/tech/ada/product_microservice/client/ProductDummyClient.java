package tech.ada.product_microservice.client;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import tech.ada.product_microservice.dto.ProductDummyDTO;

@FeignClient(value = "${client.value}", url = "${client.url}")
public interface ProductDummyClient {

    @GetMapping("/products/{id}")
    public ResponseEntity<ProductDummyDTO> getProductById(@PathVariable Long id);

}
