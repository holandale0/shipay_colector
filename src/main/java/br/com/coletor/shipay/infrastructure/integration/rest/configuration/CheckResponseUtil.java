package br.com.coletor.shipay.infrastructure.integration.rest.configuration;

import feign.FeignException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

public class CheckResponseUtil {

	private CheckResponseUtil() {
		throw new UnsupportedOperationException("constructor cannot be called");
	}

	public static ResponseEntity<Object> getErrorResponseEntity(final Throwable cause) {

		if (cause instanceof FeignException feignException) {
			if (feignException.status() != -1) {
				return ResponseEntity.status(feignException.status()).body(cause);
			}
			return ResponseEntity.status(HttpStatus.FAILED_DEPENDENCY).body(cause);
		}

		return ResponseEntity.status(HttpStatus.FAILED_DEPENDENCY).body(cause);
	}

}
