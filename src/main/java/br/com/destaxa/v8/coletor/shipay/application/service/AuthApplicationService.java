package br.com.destaxa.v8.coletor.shipay.application.service;

import br.com.destaxa.v8.coletor.shipay.infrastructure.integration.rest.auth.AuthTokenIntegrationClientImpl;
import br.com.destaxa.v8.coletor.shipay.application.dto.response.ShipayPdvsysoutLoginResponseDTO;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;

@Service
public class AuthApplicationService {

    private static final Logger LOGGER = LoggerFactory.getLogger(AuthApplicationService.class);

    @Autowired
    private AuthTokenIntegrationClientImpl tokenIntegrationClient;

    public Mono<String> generateAuthToken() {
        LOGGER.info("Generating Auth Token...");
        return Mono.defer(() -> tokenIntegrationClient.generateAuthToken()
                .map(ShipayPdvsysoutLoginResponseDTO::getAccessToken)); // Extrai o token
    }


}
