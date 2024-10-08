package br.com.coletor.shipay.application.dto.request;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ShipayTerminalRequestDTO {

    private String token;

    @JsonProperty("customer_id")
    private String customerId;

    @JsonProperty("store_uuid")
    private String storeId;
}
