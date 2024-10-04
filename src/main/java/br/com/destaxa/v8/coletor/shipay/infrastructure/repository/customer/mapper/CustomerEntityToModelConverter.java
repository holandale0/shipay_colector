package br.com.destaxa.v8.coletor.shipay.infrastructure.repository.customer.mapper;


import br.com.destaxa.v8.coletor.shipay.domain.customer.model.Customer;
import br.com.destaxa.v8.coletor.shipay.infrastructure.repository.customer.entity.CustomerEntity;
import org.springframework.stereotype.Component;

import java.util.Arrays;

@Component
public class CustomerEntityToModelConverter {

	public Customer toModel(CustomerEntity entity) {
		return Customer.builder()
				.id(entity.getId())
				.clientId(entity.getClientId())
				.externalId(entity.getExternalId())
				.document(entity.getDocument())
				.acquirer(entity.getAcquirer())
				.reservationDate(entity.getReservationDate())
				.storePosId(entity.getStorePosId())
				.build();
	}
}
