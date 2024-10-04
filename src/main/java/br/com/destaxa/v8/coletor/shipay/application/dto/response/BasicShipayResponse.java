package br.com.destaxa.v8.coletor.shipay.application.dto.response;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class BasicShipayResponse {

  Integer offset;

  Integer total;

  Integer count;

}
