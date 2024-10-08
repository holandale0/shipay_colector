package br.com.coletor.shipay.domain.customer.service;


import br.com.coletor.shipay.domain.customer.exception.CustomerServiceException;
import br.com.coletor.shipay.domain.customer.model.Customer;
import br.com.coletor.shipay.domain.customer.repository.CustomerRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;

@Service
public class CustomerServiceImpl implements CustomerService {

	private static final Logger LOGGER = LoggerFactory.getLogger(CustomerServiceImpl.class);

	@Autowired
	private CustomerRepository customerRepository;

	@Override
	public Flux<Customer> getCustomers() {
		return customerRepository.findCustomers()
				.doOnSubscribe(subscription -> LOGGER.info("Fetching customers from the repository"))
				.doOnNext(customer -> LOGGER.info("Fetched customer: {}", customer))
				.doOnError(e -> LOGGER.error("DOMAIN - EXCEPTION - Error fetching customers: {}", e.getMessage(), e))
				.onErrorMap(e -> new CustomerServiceException("DOMAIN - EXCEPTION - Error fetching customers", e));
	}

}
