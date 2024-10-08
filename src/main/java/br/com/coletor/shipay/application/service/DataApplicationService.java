package br.com.coletor.shipay.application.service;

import br.com.coletor.shipay.application.dto.response.ShipayOrdersResponseDTO;
import br.com.coletor.shipay.application.dto.response.ShipayTerminalResponseDTO;
import br.com.coletor.shipay.domain.customer.model.Customer;
import br.com.coletor.shipay.infrastructure.integration.rest.transactions.DataIntegrationClient;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;


@Service
public class DataApplicationService {

    private static final Logger LOGGER = LoggerFactory.getLogger(DataApplicationService.class);

    @Autowired
    private DataIntegrationClient dataIntegrationClient;

    public Flux<ShipayOrdersResponseDTO> processTransactions(String authToken, Customer customer, ShipayTerminalResponseDTO terminal) {
        LOGGER.info("Fetching transactions for terminal: {}", terminal.getTerminalId());
        return Mono.fromCallable(() -> dataIntegrationClient.getData(
                        authToken,
                        1, // offset
                        500, // limit
                        customer.getLastColectDate(),
                        customer.getExternalId(),
                        terminal.getTerminalId()).getBody().getOrders())
                .flatMapMany(Flux::fromIterable)
                .onErrorResume(throwable -> {
                    LOGGER.error("Error fetching transactions for terminal {}: {}", terminal.getTerminalId(), throwable.getMessage(), throwable);
                    return Flux.empty();
                });
    }


}
