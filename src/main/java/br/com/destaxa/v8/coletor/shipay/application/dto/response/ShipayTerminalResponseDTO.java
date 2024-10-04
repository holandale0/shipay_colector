package br.com.destaxa.v8.coletor.shipay.application.dto.response;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;
import org.springframework.stereotype.Component;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Component
public class ShipayTerminalResponseDTO {

  @JsonProperty("active")
  private boolean active;

  @JsonProperty("category")
  private String category;

  @JsonProperty("id")
  private String terminalId;

  @JsonProperty("name")
  private String name;

}
