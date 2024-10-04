package br.com.destaxa.v8.coletor.shipay.application.service;

import br.com.destaxa.v8.coletor.shipay.application.dto.request.ConciliantionIntentRequestDTO;
import br.com.destaxa.v8.coletor.shipay.application.dto.request.psp.PspProviderType;
import br.com.destaxa.v8.coletor.shipay.domain.customer.model.Customer;
import br.com.destaxa.v8.coletor.shipay.infrastructure.integration.rest.transactions.ConciliationIntentIntegrationClient;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;

@Service
public class ConciliationIntentApplicationService {

    private static final Logger LOGGER = LoggerFactory.getLogger(ConciliationIntentApplicationService.class);

    @Autowired
    private ConciliationIntentIntegrationClient conciliationIntentIntegrationClient;

    public Mono<Void> checkOrCreateIntent(String authToken, Customer customer) {
        return Mono.defer(() -> {
            LOGGER.info("Checking or creating intent for customer: {}", customer.getExternalId());
            return Mono.fromCallable(() -> conciliationIntentIntegrationClient.getConciliationIntent(authToken))
                    .map(ResponseEntity::getBody) // Extrai o corpo da resposta
                    .flatMap(intentResponse -> {
                        if (!intentResponse.getActive()) {
                            return createConciliationIntent(authToken, customer); // Se não estiver ativa, cria nova intenção
                        }
                        return Mono.empty(); // Se já estiver ativa, retorna vazio
                    });
        });
    }

    private Mono<Void> createConciliationIntent(String authToken, Customer customer) {
        LOGGER.info("Creating new conciliation intent for customer: {}", customer.getExternalId());
        ConciliantionIntentRequestDTO request = new ConciliantionIntentRequestDTO();
        request.setPspProvider(PspProviderType.tbanks.name());
        request.setStorePosId(customer.getStorePosId());
        request.setCustomerId(customer.getExternalId());

        return Mono.fromCallable(() -> conciliationIntentIntegrationClient.createConciliationIntent(request, authToken))
                .map(ResponseEntity::getBody) // Extrai o corpo da resposta
                .flatMap(intentResponse -> {
                    if (!intentResponse.getActive()) {
                        return Mono.error(new RuntimeException("Failed to create active conciliation intent"));
                    }
                    return Mono.empty(); // Intenção criada ou já ativa
                });
    }



}
