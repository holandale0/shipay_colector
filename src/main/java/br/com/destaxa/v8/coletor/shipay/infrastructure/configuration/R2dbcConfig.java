package br.com.destaxa.v8.coletor.shipay.infrastructure.configuration;

import io.r2dbc.spi.ConnectionFactories;
import io.r2dbc.spi.ConnectionFactory;
import io.r2dbc.spi.ConnectionFactoryOptions;
import io.r2dbc.spi.Option;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.r2dbc.config.AbstractR2dbcConfiguration;
import org.springframework.data.r2dbc.repository.config.EnableR2dbcRepositories;
import org.springframework.r2dbc.connection.init.ConnectionFactoryInitializer;

@Configuration
@EnableR2dbcRepositories
public class R2dbcConfig extends AbstractR2dbcConfiguration {

	private static final Logger LOGGER = LoggerFactory.getLogger(AbstractR2dbcConfiguration.class);

	private static final String driver = "postgresql";

	@Value("${r2dbc.host}")
	private String host;

	@Value("${r2dbc.port}")
	private int port;

	@Value("${r2dbc.database}")
	private String database;

	@Value("${r2dbc.username}")
	private String user;

	@Value("${r2dbc.password}")
	private String password;

	@Value("${r2dbc.properties.schema}")
	private String schema;

	@Override
	@Bean
	public ConnectionFactory connectionFactory() {
		try {
			var options = ConnectionFactoryOptions.builder().option(ConnectionFactoryOptions.DRIVER, driver)
					.option(ConnectionFactoryOptions.HOST, host).option(ConnectionFactoryOptions.PORT, port)
					.option(ConnectionFactoryOptions.DATABASE, database).option(ConnectionFactoryOptions.USER, user)
					.option(ConnectionFactoryOptions.PASSWORD, password).option(Option.valueOf("schema"), schema)
					// .option(PostgresqlConnectionFactoryProvider.OPTIONS,
					// Map.of("lock_timeout", "30s"))
					.build();

			return ConnectionFactories.get(options);
		}
		catch (Exception e) {
			LOGGER.error("Error creating ConnectionFactory: {}", e.getMessage(), e);
			throw e;
		}
	}

	@Bean
	public ConnectionFactoryInitializer initializer(ConnectionFactory connectionFactory) {
		try {
			ConnectionFactoryInitializer initializer = new ConnectionFactoryInitializer();
			initializer.setConnectionFactory(connectionFactory);
			return initializer;
		}
		catch (Exception e) {
			LOGGER.error("Error initializing ConnectionFactory: {}", e.getMessage(), e);
			throw e;
		}
	}

}