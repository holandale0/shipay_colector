package br.com.coletor.shipay.infrastructure.repository.customer.mapper;


import br.com.coletor.shipay.domain.customer.model.Customer;
import br.com.coletor.shipay.infrastructure.repository.customer.entity.CustomerEntity;
import org.springframework.stereotype.Component;

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
