package br.com.coletor.shipay.domain.integrationError.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

import java.time.LocalDateTime;
import java.util.UUID;

@SuperBuilder
@Data
@AllArgsConstructor
@NoArgsConstructor
public class IntegrationError {

	@Builder.Default
	private UUID id = UUID.randomUUID();

	private String externalId;

	@Builder.Default
	private String acquirer = "SHIPAY";

	@Builder.Default
	private String colector = "SHIPAY";

	private String document;

	@Builder.Default
	private LocalDateTime colectDatetime = LocalDateTime.now();

	private String errorMessage;

}
