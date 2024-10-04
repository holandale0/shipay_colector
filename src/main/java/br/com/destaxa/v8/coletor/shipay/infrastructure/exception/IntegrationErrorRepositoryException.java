package br.com.destaxa.v8.coletor.shipay.infrastructure.exception;

public class IntegrationErrorRepositoryException extends RuntimeException {

	public IntegrationErrorRepositoryException(String message, Throwable throwable) {
		super(message, throwable);
	}

}
