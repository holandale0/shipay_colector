package br.com.coletor.shipay.infrastructure.exception;

public class ColectorDatalogRepositoryException extends RuntimeException {

	public ColectorDatalogRepositoryException(String message, Throwable throwable) {
		super(message, throwable);
	}

}
