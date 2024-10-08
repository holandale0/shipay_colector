package br.com.coletor.shipay.domain.customer.repository;


import br.com.coletor.shipay.domain.customer.model.Customer;
import reactor.core.publisher.Flux;

public interface CustomerRepository {

	Flux<Customer> findCustomers();

}
