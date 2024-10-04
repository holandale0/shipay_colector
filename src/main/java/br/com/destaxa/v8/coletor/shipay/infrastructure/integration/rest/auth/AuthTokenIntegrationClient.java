package br.com.destaxa.v8.coletor.shipay.infrastructure.integration.rest.auth;


import br.com.destaxa.v8.coletor.shipay.application.dto.request.ShipayPdvauthLoginRequestDTO;
import br.com.destaxa.v8.coletor.shipay.application.dto.response.ShipayPdvsysoutLoginResponseDTO;
import br.com.destaxa.v8.coletor.shipay.infrastructure.integration.rest.configuration.CheckResponseUtil;
import feign.Headers;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.cloud.openfeign.FeignClientProperties.FeignClientConfiguration;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@FeignClient(name = "auth-api", url = "${integration.shipay.url}", configuration = FeignClientConfiguration.class )
@Headers(value = "Content-Type: application/json")
public interface AuthTokenIntegrationClient {

  static final Logger LOGGER = LoggerFactory.getLogger(AuthTokenIntegrationClient.class);

  @PostMapping("/pdvauth")
  @Headers(value = "Accept: application/json")
  ResponseEntity<ShipayPdvsysoutLoginResponseDTO> generatePdvAuthToken(ShipayPdvauthLoginRequestDTO request);

  @PostMapping("/pdvsysauth")
  @Headers(value = "Accept: application/json")
  ResponseEntity<ShipayPdvsysoutLoginResponseDTO> generatePdvSysAuthToken(@RequestBody ShipayPdvsysoutLoginRequestDTO request);


  default ResponseEntity<Object> defaultFallback(String clientId, String clientSecret, String grantType,
                                                 String username, String password, Throwable cause) {
    LOGGER.error("INFRASTRUCTURE - INTEGRATION - EXCEPTION - Fallback method called due to: {}", cause.getMessage(),
            cause);
    return CheckResponseUtil.getErrorResponseEntity(cause);
  }


}
