package br.com.coletor.shipay.application.service;

import br.com.coletor.shipay.application.dto.response.ShipayOrdersResponseDTO;
import br.com.coletor.shipay.application.dto.response.ShipayStoreResponseDTO;
import br.com.coletor.shipay.application.dto.response.ShipayTerminalResponseDTO;
import br.com.coletor.shipay.application.mapper.DataTransactionMapper;
import br.com.coletor.shipay.domain.customer.model.Customer;
import br.com.coletor.shipay.domain.customer.service.CustomerService;
import br.com.coletor.shipay.domain.transaction.model.ExternalTransaction;
import br.com.coletor.shipay.domain.transaction.service.ExternalTransactionService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;

@Service
public class ShipayColectorApplicationService {

    private static final Logger LOGGER = LoggerFactory.getLogger(ShipayColectorApplicationService.class);

    @Autowired
    @Lazy
    private CustomerService customerService;

    @Autowired
    @Lazy
    private AuthApplicationService authService;

    @Autowired
    @Lazy
    private ConciliationIntentApplicationService conciliationIntentService;

    @Autowired
    @Lazy
    private StoreApplicationService storeService;

    @Autowired
    @Lazy
    private TerminalApplicationService terminalService;

    @Autowired
    @Lazy
    private DataApplicationService dataService;

    @Autowired
    private ExternalTransactionService externalTransactionService;

    @Autowired
    private DataTransactionMapper mapper;

    /**
     * Método principal que inicia o fluxo de coleta de dados.
     * @return Mono<Void>
     */
    public Mono<Void> getPixData() {
        LOGGER.info("Starting process to get Pix data");
        return customerService.getCustomers()
                .flatMap(this::processCustomer)
                .then()
                .doOnTerminate(() -> LOGGER.info("Finished processing Pix data"));
    }

    /**
     * Processa cada cliente individualmente.
     * @param customer O cliente a ser processado.
     * @return Mono<Void>
     */
    private Mono<Void> processCustomer(Customer customer) {
        if (customer == null) {
            LOGGER.warn("No customer found, returning empty Mono");
            return Mono.empty();
        }

        LOGGER.info("Processing customer: {}", customer);

        return authService.generateAuthToken()
                .flatMap(authToken -> processCustomerData(authToken, customer))
                .then()
                .onErrorResume(throwable -> {
                    LOGGER.error("Error processing customer {}: {}", customer.getExternalId(), throwable.getMessage(), throwable);
                    return Mono.empty();
                });
    }


    private Mono<Void> processCustomerData(String authToken, Customer customer) {
        return conciliationIntentService.checkOrCreateIntent(authToken, customer)
                .thenMany(storeService.processStores(authToken, customer)) // Flux<ShipayStoreResponseDTO>
                .flatMap(store -> processStoreTerminals(authToken, customer, store))
                .then();
    }


    private Mono<Void> processStoreTerminals(String authToken, Customer customer, ShipayStoreResponseDTO store) {
        return terminalService.processTerminals(authToken, customer, store) // Flux<ShipayTerminalResponseDTO>
                .flatMap(terminal -> processTerminalTransactions(authToken, customer, terminal))
                .then();
    }


    private Mono<Void> processTerminalTransactions(String authToken, Customer customer, ShipayTerminalResponseDTO terminal) {
        return dataService.processTransactions(authToken, customer, terminal) // Flux<ShipayOrdersResponseDTO>
                .map(this::mapToExternalTransaction)
                .flatMap(externalTransactionService::saveTransaction)
                .then();
    }


    /**
     * Mapeia um ShipayOrdersResponseDTO para ExternalTransaction.
     * @param order O objeto ShipayOrdersResponseDTO
     * @return ExternalTransaction
     */
    private ExternalTransaction mapToExternalTransaction(ShipayOrdersResponseDTO order) {
        LOGGER.info("Mapping ShipayOrdersResponseDTO to ExternalTransaction: {}", order);
        return mapper.toTransaction(order);
    }

}
