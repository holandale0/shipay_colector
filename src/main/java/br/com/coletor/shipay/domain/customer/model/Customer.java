package br.com.coletor.shipay.domain.customer.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.UUID;

@SuperBuilder
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Customer {

	private UUID id;

	private UUID clientId;

	private String externalId;

	private String document;

	private String acquirer;

	private LocalDate lastColectDate;

	private LocalDateTime reservationDate;

	private String storePosId;

	private int page = 1;

	private int pageSize = 500;

	private String secretKey;

	private String accessKey;

}
