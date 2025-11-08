package br.com.asa.product_microservice.infrastructure.adapter.out.web.client;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import br.com.asa.product_microservice.infrastructure.dto.ProductDummyDTO;

@FeignClient(value = "${client.value}", url = "${client.url}")
public interface ProductDummyClient {

    @GetMapping("/products/{id}")
    public ResponseEntity<ProductDummyDTO> getProductById(@PathVariable Long id);

}
