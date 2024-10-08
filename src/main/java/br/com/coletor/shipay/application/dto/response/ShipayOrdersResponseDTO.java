package br.com.coletor.shipay.application.dto.response;

import com.fasterxml.jackson.annotation.JsonProperty;
import java.util.Date;

import lombok.*;
import org.springframework.stereotype.Component;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Component
public class ShipayOrdersResponseDTO {

  @JsonProperty("order_id")
  private String orderId;

  @JsonProperty("order_status")
  private String orderStatus;

  @JsonProperty("pix_txid")
  private String pixTxid;

  @JsonProperty("order_ref")
  private String orderRef;

  @JsonProperty("customer_id")
  private String customerId;

  @JsonProperty("store_cnpj_cpf")
  private String storeCnpjCPF;

  @JsonProperty("wallet")
  private String wallet;

  @JsonProperty("pix_psp")
  private String pixPsp;

  @JsonProperty("payment_date")
  private Date paymentDate;

  @JsonProperty("wallet_payment_id")
  private String walletPaymentId;

  @JsonProperty("total_order")
  private Integer totalOrder;

  @JsonProperty("fee")
  private Integer fee;

  @JsonProperty("transfer_amount")
  private Integer transferAmount;

  @JsonProperty("transfer_date")
  private Date transferDate;

  @JsonProperty("transfer_e2eid")
  private String transferE2eid;

  @JsonProperty("reconciliation_status")
  private String reconciliationStatus;

}
