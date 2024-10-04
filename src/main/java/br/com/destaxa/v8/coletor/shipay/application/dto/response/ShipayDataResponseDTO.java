package br.com.destaxa.v8.coletor.shipay.application.dto.response;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;
import org.springframework.stereotype.Component;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Component
public class ShipayDataResponseDTO {

  @JsonProperty("total")
  private Integer total;

  @JsonProperty("offset")
  private Integer offset;

  @JsonProperty("count")
  private Integer count;

  @JsonProperty("limit")
  private Integer limit;

  @JsonProperty("orders")
  private List<ShipayOrdersResponseDTO> orders;


}
