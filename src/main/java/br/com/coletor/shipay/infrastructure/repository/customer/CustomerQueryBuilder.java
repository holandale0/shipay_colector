package br.com.coletor.shipay.infrastructure.repository.customer;


import br.com.coletor.shipay.infrastructure.repository.customer.entity.CustomerEntity;
import io.r2dbc.spi.Row;
import io.r2dbc.spi.RowMetadata;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.r2dbc.core.DatabaseClient;
import org.springframework.stereotype.Component;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.UUID;

@Component
public class CustomerQueryBuilder {

	//private static final String BASE_QUERY = "SELECT * FROM fn_task_colector(:colectorName, :podName)";

	private static final String BASE_QUERY = "SELECT * FROM shipay_mocked_customers(:colectorName, :podName)";

	@Value("${pod.name:shipay-test-pod-1}")
	private String podName;

	private String colectorName = "SHIPAY";

	public DatabaseClient.GenericExecuteSpec buildFindCustomersQuery(DatabaseClient databaseClient) {
		return databaseClient.sql(BASE_QUERY).bind("colectorName", colectorName).bind("podName", podName);
	}

	public CustomerEntity mapRow(Row row, RowMetadata metadata) {
		CustomerEntity entity = new CustomerEntity();
		entity.setId(row.get("id", UUID.class));
		entity.setClientId(row.get("client_id", UUID.class));
		entity.setExternalId(row.get("external_id", String.class));
		entity.setDocument(row.get("document", String.class));
		entity.setReservationDate(row.get("reservation_date", LocalDateTime.class));
		entity.setLastColectDate(row.get("enqueue_datetime", LocalDate.class));
		entity.setAcquirer(row.get("acquirer", String.class));
		entity.setStorePosId(row.get("store_pos_id", String.class));

		return entity;
	}

}