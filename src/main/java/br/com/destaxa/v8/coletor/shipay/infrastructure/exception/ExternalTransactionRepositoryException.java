package br.com.destaxa.v8.coletor.shipay.infrastructure.exception;

public class ExternalTransactionRepositoryException extends RuntimeException {

	public ExternalTransactionRepositoryException(String message, Throwable cause) {
		super(message, cause);
	}

	public ExternalTransactionRepositoryException(String message) {
	}

}
