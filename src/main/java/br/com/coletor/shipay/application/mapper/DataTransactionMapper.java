package br.com.coletor.shipay.application.mapper;

import br.com.coletor.shipay.application.dto.response.ShipayOrdersResponseDTO;
import br.com.coletor.shipay.domain.transaction.model.ExternalTransaction;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface DataTransactionMapper {

  @Mapping(target = "clientFk", source = "customerId")
  @Mapping(target = "acquirer", defaultValue = "Shipay", ignore = true)
  @Mapping(target = "colector", defaultValue = "Shipay", ignore = true)
  @Mapping(target = "document", source = "storeCnpjCPF")
  @Mapping(target = "transactionDatetime", source = "paymentDate")
  @Mapping(target = "settlementDatetime", source = "transferDate")
  @Mapping(target = "grossValue", source = "totalOrder")
  @Mapping(target = "fee", source = "fee")
  @Mapping(target = "netValue", source = "transferAmount")
  @Mapping(target = "totalInstallments", defaultValue = "1", ignore = true)
  @Mapping(target = "installment", defaultValue = "1", ignore = true)
  @Mapping(target = "salesId", source = "orderId")
  @Mapping(target = "transactionCode", source = "pixTxid")
  @Mapping(target = "authorizationCode", source = "walletPaymentId")
  @Mapping(target = "transactionType", defaultValue = "PIX", ignore = true)
  @Mapping(target = "destaxaTransaction", defaultValue = "true", ignore = true)
  @Mapping(target = "terminalId", source = "customerId")
  @Mapping(target = "lotCode", source = "transferE2eid")
  @Mapping(target = "lotStatus", source = "reconciliationStatus")
  @Mapping(target = "transactionStatus", source = "orderStatus")
  @Mapping(target = "usn", source = "orderRef")
  ExternalTransaction toTransaction(ShipayOrdersResponseDTO response);

}
