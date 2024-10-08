package br.com.coletor.shipay.application.dto.request;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;


@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ConciliantionIntentRequestDTO {

  String token;

  @JsonProperty("psp_provider")
  String pspProvider;

  @JsonProperty("store_pos_id")
  String storePosId;

  @JsonProperty("customer_id")
  String customerId;

  /*

  {
    "psp_provider": "original",
    "store_pos_id": "be396fee-ddc0-4a45-b169-5c271f88be7b",
    "customer_id": "1a1cdf03-31d3-4f65-9de5-9108b2a61bf1"
  }

   */

}
