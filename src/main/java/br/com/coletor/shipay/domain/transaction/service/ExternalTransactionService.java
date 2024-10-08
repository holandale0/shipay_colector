package br.com.coletor.shipay.domain.transaction.service;

import br.com.coletor.shipay.domain.transaction.model.ExternalTransaction;
import reactor.core.publisher.Mono;

public interface ExternalTransactionService {

    Mono<Void> saveTransaction(ExternalTransaction externalTransaction);
}
