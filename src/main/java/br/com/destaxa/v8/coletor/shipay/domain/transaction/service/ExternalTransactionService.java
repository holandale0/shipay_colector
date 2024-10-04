package br.com.destaxa.v8.coletor.shipay.domain.transaction.service;

import br.com.destaxa.v8.coletor.shipay.domain.transaction.model.ExternalTransaction;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.List;

public interface ExternalTransactionService {

    Mono<Void> saveTransaction(ExternalTransaction externalTransaction);
}
