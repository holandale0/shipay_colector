package br.com.coletor.shipay.application.dto.response;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;
import org.springframework.stereotype.Component;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Component
public class ShipayConciliationIntentResponseDTO {

  @JsonProperty("id")
  private String intentId;

  @JsonProperty("active")
  private Boolean active;

  @JsonProperty("accepted_at")
  private String acceptedAt;

  @JsonProperty("type")
  private String type;

  @JsonProperty("store_id")
  private String storeId;

  @JsonProperty("detail")
  private String detail;

  /*
  {
"id": 3,
"accepted_at": "2021-05-18T18:16:21",
"active": true,
"store_id": "0376cdca-b19b-46ee-9e79-f6e1a862f887",
"type": "RECONCILIATION_PIX_ORIGINAL"
}
   */

}
