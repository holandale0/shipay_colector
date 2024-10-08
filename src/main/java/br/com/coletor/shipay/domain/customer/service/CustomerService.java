package br.com.coletor.shipay.domain.customer.service;


import br.com.coletor.shipay.domain.customer.model.Customer;
import reactor.core.publisher.Flux;

public interface CustomerService {

	Flux<Customer> getCustomers();

}
