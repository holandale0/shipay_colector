package br.com.destaxa.v8.coletor.shipay.domain.integrationError.service;


import br.com.destaxa.v8.coletor.shipay.domain.integrationError.exception.IntegrationErrorServiceException;
import br.com.destaxa.v8.coletor.shipay.domain.integrationError.model.IntegrationError;
import br.com.destaxa.v8.coletor.shipay.domain.integrationError.repository.IntegrationErrorRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;
import reactor.core.publisher.Mono;

//@Service
public class IntegrationErrorServiceImpl implements IntegrationErrorService {

	private static final Logger LOGGER = LoggerFactory.getLogger(IntegrationErrorServiceImpl.class);

	//@Autowired
	private IntegrationErrorRepository repository;

	@Override
	public Mono<Void> saveIntegrationError(IntegrationError integrationError) {

		Assert.notNull(integrationError, "DOMAIN - EXCEPTION - Object to save must not be null");

		return repository.save(integrationError)
				.doOnSuccess(saved -> LOGGER.info("DOMAIN - Integration error infos saved successfully"))
				.doOnError(throwable -> {
					LOGGER.error("DOMAIN - EXCEPTION - Error saving integration error infos: {}",
							throwable.getMessage(), throwable);
				}).then().onErrorResume(throwable -> Mono.error(new IntegrationErrorServiceException(
						"DOMAIN - EXCEPTION - Error saving integration error infos", throwable)));
	}

}
