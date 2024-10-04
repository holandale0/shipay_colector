package br.com.destaxa.v8.coletor.shipay.application.service;

import br.com.destaxa.v8.coletor.shipay.application.dto.response.ShipayTerminalResponseDTO;
import br.com.destaxa.v8.coletor.shipay.application.dto.response.ShipayStoreResponseDTO;
import br.com.destaxa.v8.coletor.shipay.domain.customer.model.Customer;
import br.com.destaxa.v8.coletor.shipay.infrastructure.integration.rest.transactions.TerminalIntegrationClient;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Service
public class TerminalApplicationService {

    private static final Logger LOGGER = LoggerFactory.getLogger(TerminalApplicationService.class);

    @Autowired
    private TerminalIntegrationClient terminalIntegrationClient;

    public Flux<ShipayTerminalResponseDTO> processTerminals(String authToken, Customer customer, ShipayStoreResponseDTO store) {
        LOGGER.info("Fetching terminals for store: {}", store.getStoreId());
        return Mono.fromCallable(() -> terminalIntegrationClient.getStoresTerminal(authToken, customer.getExternalId(), store.getStoreId()).getBody())
                .flatMapMany(Flux::fromIterable)
                .onErrorResume(throwable -> {
                    LOGGER.error("Error fetching terminals for store {}: {}", store.getStoreId(), throwable.getMessage(), throwable);
                    return Flux.empty();
                });
    }


}
