package br.com.destaxa.v8.coletor.shipay.infrastructure.repository.transaction.mapper;


import br.com.destaxa.v8.coletor.shipay.domain.transaction.model.ExternalTransaction;
import br.com.destaxa.v8.coletor.shipay.infrastructure.repository.transaction.entity.ExternalTransactionEntity;
import org.springframework.stereotype.Component;

@Component
public class ExternalTransactionModelToEntityMapper {

	public ExternalTransactionEntity toEntity(ExternalTransaction model) {
		ExternalTransactionEntity entity = new ExternalTransactionEntity();
		entity.setId(model.getId());
		entity.setExternalId(model.getExternalId());
		entity.setAcquirer(model.getAcquirer());
		entity.setColector(model.getColector());
		entity.setDocument(model.getDocument());
		entity.setTransactionDatetime(model.getTransactionDatetime());
		entity.setSettlementDatetime(model.getSettlementDatetime());
		entity.setNetValue(model.getNetValue());
		entity.setGrossValue(model.getGrossValue());
		entity.setFee(model.getFee());
		entity.setFeeValue(model.getFeeValue());
		entity.setCardFlag(model.getCardFlag());
		entity.setTotalInstallments(model.getTotalInstallments());
		entity.setInstallment(model.getInstallment());
		entity.setTransactionCode(model.getTransactionCode());
		entity.setAuthorizationCode(model.getAuthorizationCode());
		entity.setCardNumber(model.getCardNumber());
		entity.setTransactionType(model.getTransactionType());
		entity.setDestaxaTransaction(model.getDestaxaTransaction());
		entity.setTerminalId(model.getTerminalId());
		entity.setLotCode(model.getLotCode());
		entity.setLotStatus(model.getLotStatus());
		entity.setEnqueueDatetime(model.getEnqueueDatetime());
		entity.setColectDatetime(model.getColectDatetime());
		entity.setSalesId(model.getSalesId());
		entity.setSalesCode(model.getSalesCode());
		entity.setUsn(model.getUsn());
		return entity;
	}

}
