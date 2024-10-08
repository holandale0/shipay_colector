package br.com.coletor.shipay.infrastructure.exception;

public class DatabaseOperationException extends RuntimeException {

	public DatabaseOperationException(String message, Throwable cause) {
		super(message, cause);
	}

}