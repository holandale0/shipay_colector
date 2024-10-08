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
public class ShipayPdvauthLoginRequestDTO {

  @JsonProperty("secret_key")
  String secretKey;

  @JsonProperty("access_key")
  String accessKey;

  @JsonProperty("client_id")
  String clientId;

}
