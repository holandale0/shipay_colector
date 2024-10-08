package br.com.coletor.shipay.infrastructure.integration.rest.transactions;

import br.com.coletor.shipay.application.dto.request.ConciliantionIntentRequestDTO;
import br.com.coletor.shipay.application.dto.response.ShipayConciliationIntentResponseDTO;
import br.com.coletor.shipay.infrastructure.integration.rest.configuration.CheckResponseUtil;
import feign.Headers;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.cloud.openfeign.FeignClientProperties;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;

@FeignClient(name = "conciliation-api", url = "${integration.shipay.url}", configuration = FeignClientProperties.FeignClientConfiguration.class )
@Headers(value = "Content-Type: application/json")
public interface ConciliationIntentIntegrationClient {

    static final Logger LOGGER = LoggerFactory.getLogger(ConciliationIntentIntegrationClient.class);

    @PostMapping(value = "/reconciliation/v1/pix-any-bank/intention", produces = MediaType.APPLICATION_JSON_VALUE)
    @Headers(value = "Accept: application/json")
    ResponseEntity<ShipayConciliationIntentResponseDTO> createConciliationIntent(@RequestBody ConciliantionIntentRequestDTO request, @RequestHeader("Authorization") String loginData);

    @GetMapping("/reconciliation/v1/pix-any-bank/intention")
    @Headers({ "Content-Type: application/json", "Authorization: Bearer {token}" })
    ResponseEntity<ShipayConciliationIntentResponseDTO> getConciliationIntent(@RequestHeader("Authorization") String token);

    default ResponseEntity<Object> defaultFallback(Throwable cause) {
        LOGGER.error("INFRASTRUCTURE - INTEGRATION - EXCEPTION - Fallback method called due to: {}", cause.getMessage(),
                cause);
        return CheckResponseUtil.getErrorResponseEntity(cause);
    }
}
