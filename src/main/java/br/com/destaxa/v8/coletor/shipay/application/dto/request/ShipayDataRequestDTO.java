package br.com.destaxa.v8.coletor.shipay.application.dto.request;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ShipayDataRequestDTO {

  String token;

  @Builder.Default
  @JsonProperty("offset")
  private int offset = 1;

  @Builder.Default
  @JsonProperty("limit")
  private int limit = 500;

  @JsonProperty("customer_id")
  private String customerId;

  @JsonProperty("store_pos_id")
  private String terminalId;

}
