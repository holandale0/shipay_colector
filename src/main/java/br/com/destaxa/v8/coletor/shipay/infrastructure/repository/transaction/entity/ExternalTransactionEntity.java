package br.com.destaxa.v8.coletor.shipay.infrastructure.repository.transaction.entity;

import lombok.*;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Date;
import java.util.UUID;


@Data
@Getter
@Setter
@EqualsAndHashCode
public class ExternalTransactionEntity {

	UUID id;

	UUID clientFk;

	String externalId;

	String acquirer;

	String colector;

	String document;

	LocalDateTime transactionDatetime;

	LocalDateTime settlementDatetime;

	LocalDateTime enqueueDatetime;

	LocalDateTime colectDatetime;

	Double netValue;

	Double grossValue;

	Double fee;

	Double feeValue;

	String cardFlag;

	Integer totalInstallments;

	Integer installment;

	String transactionCode;

	String authorizationCode;

	String cardNumber;

	String transactionType;

	String destaxaTransaction;

	String terminalId;

	String lotCode;

	String lotStatus;

	String salesId;

	String salesCode;

	String usn;

	String transactionStatus;

}
