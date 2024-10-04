package br.com.destaxa.v8.coletor.shipay.domain.transaction.model;


import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Date;
import java.util.UUID;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;



@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ExternalTransaction {

  /*

pos_product_id é fixo, para autenticação.
access_key é fixo, para autenticação.

    "access_key": "pXKu-A9qecYmJJOHazJhCA"
    "pos_product_id": "3cd9ac70-0477-4f18-8cc9-f24ac86e3d54"


store_pos_id é por cliente (external_id), deve ser retornado na procedure de agendamento. servem para consultar transações
customer_id é por cliente (external_id), deve ser retornado na procedure de agendamento. servem para consultar transações

exemplos:
    "store_pos_id": "e594c1a4da3344978bd349cd27292eb9"
    "customer_id": "21c2fb86-89a5-4a33-804c-b16614fa30f6"


order_id
string
Id do pedido na Shipay

order_status
string
Status do pedido na Sguoay

pix_txid
string
Identificador da cobrança PIX. É o order_id Shipay sem os hífens.

order_ref
string
Número de referência do pedido no sistema do PDV.

customer_id
string
ID Shipay da conta criada.

store_cnpj_cpf
string
Cadastro Nacional de Pessoa Jurídica - CNPJ ou Cadastro de Pessoa Física - CPF da loja

wallet
string
Nome da carteira digital. Neste caso, só pode ser 'pix'.

pix_psp
string
Nome do provedor de serviço de pagamentos (PSP) no qual a cobrança PIX foi gerada.

payment_date
string
Data do pagamento.

wallet_payment_id
string
Id do pagamento no banco. No caso de pedidos ainda não pagos, o retorno é 'null'.

total_order
integer
Valor total pago do pedido.

fee
integer
Valor da taxa cobrada pelo PSP.

transfer_amount
integer
valor transferido após a dedução da Taxa.

transfer_date
string
Data de transferência do pedido.

transfer_e2eid
string
endToEndId da transferência realizada para a conta destino.

reconciliation_status
string
Status do registro da conciliação.



 {

"order_id": "6f03e327-0f41-4078-bfb8-f3bc8b02ec98",
"order_status": "approved",  -- ["approved", "refused"]
"pix_txid": "6f03e3270f414078bfb8f3bc8b02ec98",
"order_ref": "pedido-qualquer-123",
"customer_id": "1a1cdf03-31d3-4f65-9de5-9108b2a61bf1",
"store_cnpj_cpf": "52268481000114",
"wallet": "pix,",
"pix_psp": "original",
"payment_date": "2021-08-05 17:40:56-03:00,",
"wallet_payment_id": "E61701190202105261327DY5A2LMQLJ3",
"total_order": "1.01,",
"fee": "0.1,",
"transfer_amount": 0.91,
"transfer_date": "2021-08-05 22:00:31-03:00,",
"transfer_e2eid": "E9289492220210806010032650SF0AT2,", -- NSU ?
"reconciliation_status": "closed" -- "closed" - Já foram transferidos para a conta de destino. "opened" - Ainda não foram transferidos para a conta de destino.

}
   */


  UUID id;

  UUID clientFk;

  String externalId;

  String acquirer;

  String colector;

  String document;

  LocalDateTime transactionDatetime;

  LocalDateTime settlementDatetime;

  LocalDateTime enqueueDatetime;

  @Builder.Default
  LocalDateTime colectDatetime = LocalDateTime.now();

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
