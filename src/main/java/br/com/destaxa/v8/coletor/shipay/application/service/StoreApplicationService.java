package br.com.destaxa.v8.coletor.shipay.application.service;

import br.com.destaxa.v8.coletor.shipay.application.dto.response.ShipayStoreResponseDTO;
import br.com.destaxa.v8.coletor.shipay.domain.customer.model.Customer;
import br.com.destaxa.v8.coletor.shipay.infrastructure.integration.rest.transactions.StoreIntegrationClient;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;


@Service
public class StoreApplicationService {

    private static final Logger LOGGER = LoggerFactory.getLogger(StoreApplicationService.class);

    @Autowired
    private StoreIntegrationClient storeIntegrationClient;

    public Flux<ShipayStoreResponseDTO> processStores(String authToken, Customer customer) {
        LOGGER.info("Fetching stores for customer: {}", customer.getExternalId());
        return Mono.fromCallable(() -> storeIntegrationClient.getStores(authToken, customer.getExternalId()).getBody())
                .flatMapMany(Flux::fromIterable)
                .onErrorResume(throwable -> {
                    LOGGER.error("Error fetching stores for customer {}: {}", customer.getExternalId(), throwable.getMessage(), throwable);
                    return Flux.empty();
                });
    }


}
