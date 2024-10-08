package br.com.coletor.shipay.infrastructure.exception;

public class CustomerRepositoryException extends RuntimeException {

	public CustomerRepositoryException(String message, Throwable cause) {
		super(message, cause);
	}

}
