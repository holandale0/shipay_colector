package br.com.destaxa.v8.coletor.shipay.domain.integrationError.repository;


import br.com.destaxa.v8.coletor.shipay.domain.integrationError.model.IntegrationError;
import reactor.core.publisher.Mono;

public interface IntegrationErrorRepository {

	Mono<Void> save(IntegrationError integrationError);

}
