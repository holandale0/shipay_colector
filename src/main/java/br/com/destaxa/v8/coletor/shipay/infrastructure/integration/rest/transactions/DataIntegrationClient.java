package br.com.destaxa.v8.coletor.shipay.infrastructure.integration.rest.transactions;


import br.com.destaxa.v8.coletor.shipay.application.dto.response.ShipayDataResponseDTO;
import br.com.destaxa.v8.coletor.shipay.infrastructure.integration.rest.configuration.CheckResponseUtil;
import feign.Headers;

import java.time.LocalDate;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.cloud.openfeign.FeignClientProperties.FeignClientConfiguration;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestParam;

@FeignClient(name = "conciliation-api", url = "${integration.shipay.url}", configuration = FeignClientConfiguration.class )
@Headers(value = "Content-Type: application/json")
public interface DataIntegrationClient {

  static final Logger LOGGER = LoggerFactory.getLogger(DataIntegrationClient.class);

  @GetMapping(value = "/reconciliation/v2/pix-any-bank")
  @Headers({ "Content-Type: application/json", "Authorization: Bearer {token}" })
  ResponseEntity<ShipayDataResponseDTO> getData(@RequestHeader("Authorization") String token,
                                                @RequestParam("offset") int offset,
                                                @RequestParam("limit") int limit,
                                                @RequestParam("start_date") LocalDate lastRequest,
                                                @RequestParam("customer_id") String customerId,
                                                @RequestParam("store_pos_id") String storePosId
                                                );

  default ResponseEntity<Object> defaultFallback(String customerId, Throwable cause) {
    LOGGER.error("INFRASTRUCTURE - INTEGRATION - EXCEPTION - Fallback method called due to: {}", cause.getMessage(),
            cause);
    return CheckResponseUtil.getErrorResponseEntity(cause);
  }

}
