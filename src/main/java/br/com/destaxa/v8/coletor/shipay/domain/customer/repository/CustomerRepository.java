package br.com.destaxa.v8.coletor.shipay.domain.customer.repository;


import br.com.destaxa.v8.coletor.shipay.domain.customer.model.Customer;
import reactor.core.publisher.Flux;

public interface CustomerRepository {

	Flux<Customer> findCustomers();

}
