package br.com.destaxa.v8.coletor.shipay.application.dto.request;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ShipayStoreRequestDTO {

    private String token;

    @JsonProperty("customer_id")
    private String customerId;
}
