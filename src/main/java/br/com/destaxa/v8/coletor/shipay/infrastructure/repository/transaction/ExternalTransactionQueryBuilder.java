package br.com.destaxa.v8.coletor.shipay.infrastructure.repository.transaction;


import br.com.destaxa.v8.coletor.shipay.infrastructure.repository.transaction.entity.ExternalTransactionEntity;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.r2dbc.core.DatabaseClient;
import org.springframework.stereotype.Component;
import reactor.core.publisher.Mono;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.UUID;

@Component
public class ExternalTransactionQueryBuilder {

	private static final Logger LOGGER = LoggerFactory.getLogger(ExternalTransactionQueryBuilder.class);

	@Autowired
	private DatabaseClient databaseClient;

	public Mono<Long> executeSaveTransaction(ExternalTransactionEntity entity) {

		UUID id = entity.getId() != null ? entity.getId() : UUID.randomUUID();

		DatabaseClient.GenericExecuteSpec sql = databaseClient.sql("""
				    CALL process_external_transaction(
				        :id, :externalId, :acquirer, :colector, :document, :transactionDatetime,
				        :settlementDatetime, :enqueueDatetime, :colectDatetime, :netValue, :grossValue,
				        :fee, :feeValue, :cardFlag, :cardNumber, :totalInstallments, :installment,
				        :transactionCode, :authorizationCode, :transactionType, :paymentChannel,
				        :destaxaTransaction, :terminalId, :lotCode, :lotStatus, :settlementType,
				        :event, :salesId, :salesCode, :usn
				    )
				""").bind("id", id);

		sql = bindField(sql, "externalId", entity.getExternalId(), String.class);
		sql = bindField(sql, "acquirer", entity.getAcquirer(), String.class);
		sql = bindField(sql, "colector", entity.getColector(), String.class);
		sql = bindField(sql, "document", entity.getDocument(), String.class);
		sql = bindField(sql, "transactionDatetime", entity.getTransactionDatetime(), LocalDateTime.class);
		sql = bindField(sql, "settlementDatetime", entity.getSettlementDatetime(), LocalDateTime.class);
		sql = bindField(sql, "enqueueDatetime", entity.getEnqueueDatetime(), LocalDateTime.class);
		sql = bindField(sql, "colectDatetime", entity.getColectDatetime(), LocalDateTime.class);
		sql = bindField(sql, "netValue", entity.getNetValue() != null ? BigDecimal.valueOf(entity.getNetValue()) : null,
				BigDecimal.class);
		sql = bindField(sql, "grossValue",
				entity.getGrossValue() != null ? BigDecimal.valueOf(entity.getGrossValue()) : null, BigDecimal.class);
		sql = bindField(sql, "fee", entity.getFee() != null ? BigDecimal.valueOf(entity.getFee()) : null,
				BigDecimal.class);
		sql = bindField(sql, "feeValue", entity.getFeeValue() != null ? BigDecimal.valueOf(entity.getFeeValue()) : null,
				BigDecimal.class);
		sql = bindField(sql, "cardFlag", entity.getCardFlag(), String.class);
		sql = bindField(sql, "cardNumber", entity.getCardNumber(), String.class);
		sql = bindField(sql, "totalInstallments", entity.getTotalInstallments(), Integer.class);
		sql = bindField(sql, "installment", entity.getInstallment(), Integer.class);
		sql = bindField(sql, "transactionCode", entity.getTransactionCode(), String.class);
		sql = bindField(sql, "authorizationCode", entity.getAuthorizationCode(), String.class);
		sql = bindField(sql, "transactionType", entity.getTransactionType(), String.class);
		//sql = bindField(sql, "paymentChannel", entity.getPaymentChannel(), String.class);
		sql = bindField(sql, "destaxaTransaction", entity.getDestaxaTransaction(), String.class);
		sql = bindField(sql, "terminalId", entity.getTerminalId(), String.class);
		sql = bindField(sql, "lotCode", entity.getLotCode(), String.class);
		sql = bindField(sql, "lotStatus", entity.getLotStatus(), String.class);
		//sql = bindField(sql, "settlementType", entity.getSettlementType(), String.class);
		//sql = bindField(sql, "event", entity.getEvent(), String.class);
		sql = bindField(sql, "salesId", entity.getSalesId(), String.class);
		sql = bindField(sql, "salesCode", entity.getSalesCode(), String.class);
		sql = bindField(sql, "usn", entity.getUsn(), String.class);

		return sql.fetch().rowsUpdated();
	}

	private <T> DatabaseClient.GenericExecuteSpec bindField(DatabaseClient.GenericExecuteSpec sql, String fieldName,
			T fieldValue, Class<T> fieldType) {
		if (fieldValue != null) {
			return sql.bind(fieldName, fieldValue);
		}
		else {
			return sql.bindNull(fieldName, fieldType);
		}
	}

}