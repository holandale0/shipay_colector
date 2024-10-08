package br.com.coletor.shipay.infrastructure.integration.rest.configuration;


import br.com.coletor.shipay.infrastructure.integration.rest.configuration.decoder.FeignClientErrorDecoder;
import com.fasterxml.jackson.databind.ObjectMapper;
import feign.Logger;
import feign.RequestInterceptor;
import feign.RequestTemplate;
import feign.Retryer;
import feign.codec.ErrorDecoder;
import feign.form.spring.SpringFormEncoder;
import io.github.resilience4j.circuitbreaker.CircuitBreakerConfig;
import io.github.resilience4j.circuitbreaker.CircuitBreakerRegistry;
import io.github.resilience4j.timelimiter.TimeLimiterConfig;
import io.opentelemetry.api.OpenTelemetry;
import io.opentelemetry.api.trace.Span;
import io.opentelemetry.context.Context;
import io.opentelemetry.context.propagation.TextMapSetter;
import io.opentelemetry.semconv.SemanticAttributes;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.autoconfigure.http.HttpMessageConverters;
import org.springframework.cloud.circuitbreaker.resilience4j.Resilience4JCircuitBreakerFactory;
import org.springframework.cloud.circuitbreaker.resilience4j.Resilience4JConfigBuilder;
import org.springframework.cloud.client.circuitbreaker.Customizer;
import org.springframework.cloud.openfeign.support.SpringEncoder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.time.Duration;

import static java.util.concurrent.TimeUnit.SECONDS;

@Configuration
public class FeignClientConfiguration {

	@Value("${feign.retry.maxAttempts}")
	private int maxAttemptsApplication;

	@Value("${feign.retry.delay.max}")
	private int maxDelayToRetry;

	@Value("${feign.retry.delay.initial}")
	private int initialDelayToRetry;

	@Bean
	public Retryer retryer() {
		return new Retryer.Default(initialDelayToRetry, SECONDS.toMillis(maxDelayToRetry), maxAttemptsApplication);
	}

	@Bean
	Logger.Level feignLoggerLevel() {
		return Logger.Level.FULL;
	}

	@Bean
	public ObjectMapper objectMapper() {
		return new ObjectMapper();
	}

	@Bean
	public ErrorDecoder errorDecoder() {
		return new FeignClientErrorDecoder();
	}

	@Bean
	public SpringFormEncoder feignFormEncoder() {
		return new SpringFormEncoder(new SpringEncoder(HttpMessageConverters::new));
	}

	@Bean
	public CircuitBreakerRegistry circuitBreakerRegistry() {
		return CircuitBreakerRegistry.ofDefaults();
	}

	@Bean
	public Customizer<Resilience4JCircuitBreakerFactory> defaultCustomizer() {
		return factory -> factory.configureDefault(id -> new Resilience4JConfigBuilder(id)
				.circuitBreakerConfig(CircuitBreakerConfig.ofDefaults())
				.timeLimiterConfig(TimeLimiterConfig.custom().timeoutDuration(Duration.ofSeconds(4)).build()).build());
	}

	private final TextMapSetter<RequestTemplate> setter = (carrier, key, value) -> {
		assert carrier != null;
		carrier.header(key, value);
	};

	@Bean
	public RequestInterceptor requestInterceptor(OpenTelemetry otel) {
		return template -> {
			Span currentSpan = Span.current();
			currentSpan.setAttribute(SemanticAttributes.HTTP_REQUEST_METHOD, template.method());
			currentSpan.setAttribute(SemanticAttributes.URL_FULL, template.url());

			otel.getPropagators().getTextMapPropagator().inject(Context.current(), template, setter);
		};
	}

}