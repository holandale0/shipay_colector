package br.com.coletor.shipay.infrastructure.integration.rest.auth;

import br.com.coletor.shipay.application.dto.response.ShipayPdvsysoutLoginResponseDTO;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import reactor.core.publisher.Mono;

@Component
public class AuthTokenIntegrationClientImpl {

	private static final Logger LOGGER = LoggerFactory.getLogger(AuthTokenIntegrationClientImpl.class);

	@Autowired
	private AuthTokenIntegrationClient authTokenIntegrationClient;

	@Autowired
	private ShipayPdvsysoutLoginRequestDTO shipayPdvsysoutLoginRequestDTO;

	public Mono<ShipayPdvsysoutLoginResponseDTO> generateAuthToken() {
		if (shipayPdvsysoutLoginRequestDTO == null) {
			return Mono.error(new RuntimeException("APPLICATION - EXCEPTION - Auth token request DTO is null"));
		}

		return Mono.defer(() -> {
			LOGGER.info("APPLICATION - Generating Auth Token...");
			ResponseEntity<ShipayPdvsysoutLoginResponseDTO> response = authTokenIntegrationClient.generatePdvSysAuthToken(
					shipayPdvsysoutLoginRequestDTO);
			return Mono.justOrEmpty(response.getBody());
		});
	}

}
