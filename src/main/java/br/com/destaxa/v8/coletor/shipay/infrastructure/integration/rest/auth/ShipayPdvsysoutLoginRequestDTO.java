package br.com.destaxa.v8.coletor.shipay.infrastructure.integration.rest.auth;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Component
public class ShipayPdvsysoutLoginRequestDTO {

    @JsonProperty("access_key")
    @Value("${integration.shipay.auth.access-key}")
    String accessKey;

    @JsonProperty("pos_product_id")
    @Value("${integration.shipay.auth.pos-product-id}")
    String posProductId;

}
