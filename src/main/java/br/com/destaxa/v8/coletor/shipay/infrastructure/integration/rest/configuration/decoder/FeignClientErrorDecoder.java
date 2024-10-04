package br.com.destaxa.v8.coletor.shipay.infrastructure.integration.rest.configuration.decoder;

import com.fasterxml.jackson.databind.ObjectMapper;
import feign.Response;
import feign.Util;
import feign.codec.ErrorDecoder;
import lombok.SneakyThrows;
import org.apache.commons.io.IOUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.nio.charset.Charset;
import java.util.Map;

public class FeignClientErrorDecoder implements ErrorDecoder {

	private static final Logger LOGGER = LoggerFactory.getLogger(FeignClientErrorDecoder.class);

	private final ObjectMapper objectMapper = new ObjectMapper();

	@SneakyThrows
	@Override
	@SuppressWarnings("unchecked")
	public Exception decode(String methodKey, Response response) {
		int status = response.status();
		String responseBody = IOUtils.toString(response.body().asReader(Charset.defaultCharset()));
		String url = response.request().url();

		LOGGER.info("INFRASTRUCTURE - INTEGRATION - Response URL: {}", url);
		LOGGER.info("INFRASTRUCTURE - INTEGRATION - Response Status: {}", status);
		LOGGER.info("INFRASTRUCTURE - INTEGRATION - Response Body: {}", responseBody);

		String message;
		try {
			// Check if the response body is empty
			if (response.body() != null) {
				String body = Util.toString(response.body().asReader(Charset.defaultCharset()));
				if (body.isEmpty()) {
					message = "Empty response body";
				}
				else {
					Map<String, Object> errorDetails = objectMapper.readValue(body, Map.class);
					message = "Error Details: " + errorDetails;
				}
			}
			else {
				message = "Null response body";
			}
		}
		catch (IOException e) {
			return new Exception("INFRASTRUCTURE - INTEGRATION - ERROR_DECODER - Erro on convert JSON ", e);
		}
		return new Exception("INFRASTRUCTURE - INTEGRATION - ERROR_DECODER - Erro on external service ("
				+ response.status() + "): " + message);
	}

}