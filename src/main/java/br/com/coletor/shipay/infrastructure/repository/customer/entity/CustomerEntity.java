package br.com.coletor.shipay.infrastructure.repository.customer.entity;



import lombok.*;


import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.UUID;


@Data
@Getter
@Setter
@EqualsAndHashCode
public class CustomerEntity {


	private UUID id;

	private UUID clientId;

	private String externalId;

	private String document;

	private String acquirer;

	private LocalDate lastColectDate;

	private LocalDateTime reservationDate;

	private String storePosId;

}
