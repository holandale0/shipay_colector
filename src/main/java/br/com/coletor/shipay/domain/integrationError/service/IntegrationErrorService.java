package br.com.coletor.shipay.domain.integrationError.service;


import br.com.coletor.shipay.domain.integrationError.model.IntegrationError;
import reactor.core.publisher.Mono;

public interface IntegrationErrorService {

	Mono<Void> saveIntegrationError(IntegrationError integrationError);

}
