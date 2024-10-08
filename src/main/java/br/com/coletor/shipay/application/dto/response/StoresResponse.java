package br.com.coletor.shipay.application.dto.response;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class StoresResponse {

  @JsonProperty("active")
  boolean active;

  @JsonProperty("cnpj_cpf")
  String document;

  @JsonProperty("id")
  String storeId;

  @JsonProperty("name")
  String name;

}
