package br.com.destaxa.v8.coletor.shipay.domain.customer.exception;

public class CustomerServiceException extends RuntimeException {

	public CustomerServiceException(String message, Throwable cause) {
		super(message, cause);
	}

	public CustomerServiceException(String message) {
		super(message);
	}

}
