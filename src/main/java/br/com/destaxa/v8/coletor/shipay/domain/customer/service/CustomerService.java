package br.com.destaxa.v8.coletor.shipay.domain.customer.service;


import br.com.destaxa.v8.coletor.shipay.domain.customer.model.Customer;
import reactor.core.publisher.Flux;

public interface CustomerService {

	Flux<Customer> getCustomers();

}
