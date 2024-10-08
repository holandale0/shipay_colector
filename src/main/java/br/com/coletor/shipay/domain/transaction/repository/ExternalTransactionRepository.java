package br.com.coletor.shipay.domain.transaction.repository;

import br.com.coletor.shipay.domain.transaction.model.ExternalTransaction;

import reactor.core.publisher.Mono;

public interface ExternalTransactionRepository {
    Mono<Void> saveAll(ExternalTransaction externalTransaction);
}
