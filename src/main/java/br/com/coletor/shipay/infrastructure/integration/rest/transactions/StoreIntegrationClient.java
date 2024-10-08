package br.com.coletor.shipay.infrastructure.integration.rest.transactions;

import br.com.coletor.shipay.infrastructure.integration.rest.configuration.CheckResponseUtil;
import br.com.coletor.shipay.application.dto.response.ShipayStoreResponseDTO;
import feign.Headers;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.cloud.openfeign.FeignClientProperties;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@FeignClient(name = "conciliation-api", url = "${integration.shipay.url}", configuration = FeignClientProperties.FeignClientConfiguration.class )
@Headers(value = "Content-Type: application/json")
public interface StoreIntegrationClient {

    static final Logger LOGGER = LoggerFactory.getLogger(StoreIntegrationClient.class);

    @GetMapping(value = "/stores")
        //@Headers({ "Content-Type: application/json", "Authorization: Bearer {token}" })
    ResponseEntity<List<ShipayStoreResponseDTO>> getStores(@RequestHeader("Authorization") String token, @RequestParam("customer_id") String customerId);

    default ResponseEntity<Object> defaultFallback(String customerId, Throwable cause) {
        LOGGER.error("INFRASTRUCTURE - INTEGRATION - EXCEPTION - Fallback method called due to: {}", cause.getMessage(),
                cause);
        return CheckResponseUtil.getErrorResponseEntity(cause);
    }
}
