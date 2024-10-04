package br.com.destaxa.v8.coletor.shipay.infrastructure.repository.customer;


import br.com.destaxa.v8.coletor.shipay.domain.customer.model.Customer;
import br.com.destaxa.v8.coletor.shipay.domain.customer.repository.CustomerRepository;
import br.com.destaxa.v8.coletor.shipay.infrastructure.exception.DataMappingException;
import br.com.destaxa.v8.coletor.shipay.infrastructure.exception.DatabaseOperationException;
import br.com.destaxa.v8.coletor.shipay.infrastructure.repository.customer.mapper.CustomerEntityToModelConverter;
import io.r2dbc.spi.Row;
import io.r2dbc.spi.RowMetadata;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.r2dbc.core.DatabaseClient;
import org.springframework.stereotype.Repository;
import reactor.core.publisher.Flux;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.UUID;

@Repository
public class CustomerReactiveJpaRepositoryImpl implements CustomerRepository {

	private static final Logger LOGGER = LoggerFactory.getLogger(CustomerReactiveJpaRepositoryImpl.class);

	@Autowired
	private DatabaseClient databaseClient;

	@Autowired
	private CustomerEntityToModelConverter toModelMapper;

	@Autowired
	private CustomerQueryBuilder customerQueryBuilder;

	@Override
	public Flux<Customer> findCustomers() {
		return customerQueryBuilder.buildFindCustomersQuery(databaseClient).map(this::mapRowToEntity).all()
				.onErrorResume(e -> {
					if (e instanceof DataMappingException) {
						throw (DataMappingException) e;
					}
					else {
						LOGGER.error("INFRASTRUCTURE - REPOSITORY - EXCEPTION - Error on execute SQL: ", e);
						return Flux.error(new DatabaseOperationException(
								"INFRASTRUCTURE - REPOSITORY - EXCEPTION - Error on execute SQL: ", e));
					}
				});
	}

	private Customer mapRowToEntity(Row row, RowMetadata metadata) {
		try {
			Customer fetchedCustomer = toModelMapper.toModel(customerQueryBuilder.mapRow(row, metadata));
			return (fetchedCustomer == null ? mockedCustomer() : fetchedCustomer);
		}
		catch (Exception e) {
			LOGGER.error("INFRASTRUCTURE - REPOSITORY - EXCEPTION - Error on mapping CustomerEntity to Customer: ", e);
			throw new DataMappingException(
					"INFRASTRUCTURE - REPOSITORY - EXCEPTION - Error on mapping CustomerEntity to Customer: ", e);
		}
	}

	private Customer mockedCustomer(){
		return Customer.builder()
				.id(UUID.fromString("2eb534bd-5247-4cc5-a58b-9a73a22d7748"))
				.secretKey("")
				.externalId("21c2fb86-89a5-4a33-804c-b16614fa30f6")
				.accessKey("pXKu-A9qecYmJJOHazJhCA")
				.storePosId("e594c1a4da3344978bd349cd27292eb9")
				.document("12119187002")
				.lastColectDate(LocalDate.now())
				.acquirer("Shipay")
				.reservationDate(LocalDateTime.now())
				.build();
	}

}
