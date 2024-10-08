package br.com.coletor.shipay.application.dto.response;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@Getter
@Setter
@NoArgsConstructor
public class ShipayPdvsysoutLoginResponseDTO {

  @JsonProperty("access_token")
  String accessToken;

  @JsonProperty("access_token_expires_in")
  Integer accessTokenExpiresIn;

  @JsonProperty("refresh_token")
  String refreshToken;

  @JsonProperty("refresh_token_expires_in")
  Integer refreshTokenExpiresIn;

  @JsonProperty("token_type")
  String tokenType;

}
