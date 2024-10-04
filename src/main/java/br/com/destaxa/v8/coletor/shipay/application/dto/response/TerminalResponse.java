package br.com.destaxa.v8.coletor.shipay.application.dto.response;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class TerminalResponse {
  @JsonProperty("active")
  boolean active;

  @JsonProperty("category")
  String category;

  @JsonProperty("id")
  String terminalId;

  @JsonProperty("name")
  String name;

}
