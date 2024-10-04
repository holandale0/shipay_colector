package br.com.destaxa.v8.coletor.shipay.application.dto.response;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;
import org.springframework.stereotype.Component;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Component
public class ShipayStoreResponseDTO {

  @JsonProperty("active")
  private boolean active;

  @JsonProperty("cnpj_cpf")
  private String document;

  @JsonProperty("id")
  private String storeId;

  @JsonProperty("name")
  private String name;

  @JsonProperty("msg")
  private String msg;

}
