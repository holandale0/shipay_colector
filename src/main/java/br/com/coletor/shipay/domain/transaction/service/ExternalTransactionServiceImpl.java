package br.com.coletor.shipay.domain.transaction.service;

import br.com.coletor.shipay.domain.transaction.model.ExternalTransaction;
import br.com.coletor.shipay.domain.transaction.repository.ExternalTransactionRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;



@Service
public class ExternalTransactionServiceImpl implements ExternalTransactionService {

    private static final Logger LOGGER = LoggerFactory.getLogger(ExternalTransactionServiceImpl.class);

    @Autowired
    private ExternalTransactionRepository repository;

    @Override
    public Mono<Void> saveTransaction(ExternalTransaction externalTransaction) {
        if (externalTransaction == null) {
            LOGGER.info("DOMAIN - No more transactions to save");
            return Mono.empty();
        }

        return repository.saveAll(externalTransaction)
                .doOnError(throwable -> {
                    LOGGER.error("DOMAIN - EXCEPTION - Error saving transactions: {}", throwable.getMessage(), throwable);
                })
                .onErrorResume(throwable -> {
                    LOGGER.error("DOMAIN - EXCEPTION - Error saving transactions: {}", throwable.getMessage(), throwable);
                    return Mono.error(new RuntimeException("DOMAIN - EXCEPTION - Error saving transactions", throwable));
                });
    }


}