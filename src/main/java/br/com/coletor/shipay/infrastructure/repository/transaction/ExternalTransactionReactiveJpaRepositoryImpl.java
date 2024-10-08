package br.com.coletor.shipay.infrastructure.repository.transaction;


import br.com.coletor.shipay.infrastructure.repository.transaction.mapper.ExternalTransactionModelToEntityMapper;
import br.com.coletor.shipay.domain.transaction.model.ExternalTransaction;
import br.com.coletor.shipay.domain.transaction.repository.ExternalTransactionRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.r2dbc.core.DatabaseClient;
import org.springframework.stereotype.Repository;
import org.springframework.util.Assert;

import reactor.core.publisher.Mono;


@Repository
public class ExternalTransactionReactiveJpaRepositoryImpl implements ExternalTransactionRepository {

	private static final Logger LOGGER = LoggerFactory.getLogger(ExternalTransactionReactiveJpaRepositoryImpl.class);

	@Autowired
	private DatabaseClient repository;

	@Autowired
	private ExternalTransactionQueryBuilder queryBuilder;

	//@Autowired
	//private IntegrationErrorRepository integrationErrorRepository;

	@Autowired
	private ExternalTransactionModelToEntityMapper mapper;

	@Override
	public Mono<Void> saveAll(ExternalTransaction externalTransaction) {
		Assert.notNull(externalTransaction, "INFRASTRUCTURE - REPOSITORY - EXCEPTION - Object to save must not be null");

		//TODO substituir o código do queryBuilder para a lógica de salvamento da shipay
		return queryBuilder.executeSaveTransaction(mapper.toEntity(externalTransaction)) // Processa o Mono<ExternalTransaction> chamando o método save
				.then() // Converte o Mono<Void> para garantir que o método retorna um Mono<Void> ao final
				.onErrorResume(throwable -> {
					LOGGER.error("INFRASTRUCTURE - REPOSITORY - EXCEPTION - Error saving transaction: {}",
							throwable.getMessage(), throwable);
					return Mono.error(new RuntimeException(
							"INFRASTRUCTURE - REPOSITORY - EXCEPTION - Error saving transaction", throwable));
				});
	}

}
