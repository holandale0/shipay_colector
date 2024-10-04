package br.com.destaxa.v8.coletor.shipay.domain.integrationError.exception;

public class IntegrationErrorServiceException extends RuntimeException {

	public IntegrationErrorServiceException(String message, Throwable throwable) {
		super(message, throwable);
	}

	public IntegrationErrorServiceException(String message) {
	}

}
