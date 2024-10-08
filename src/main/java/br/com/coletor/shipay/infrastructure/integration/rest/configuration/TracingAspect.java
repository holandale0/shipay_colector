package br.com.coletor.shipay.infrastructure.integration.rest.configuration;

import io.opentelemetry.api.OpenTelemetry;
import io.opentelemetry.api.trace.Span;
import io.opentelemetry.api.trace.SpanKind;
import io.opentelemetry.api.trace.StatusCode;
import io.opentelemetry.api.trace.Tracer;
import io.opentelemetry.context.Scope;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.Signature;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.stereotype.Component;

import java.util.Objects;

@Aspect
@Component
public class TracingAspect {

	private final OpenTelemetry otel;

	@Value("${otel.trace-depth.enabled:false}")
	private Boolean enableTraceDepth;

	@Autowired
	public TracingAspect(OpenTelemetry otel) {
		this.otel = otel;
	}

	@Around("execution(* *..destaxa..application..*.*(..))")
	public Object aroundApplicationMethod(ProceedingJoinPoint joinPoint) throws Throwable {
		return aroundMethod(joinPoint);
	}

	@Around("execution(* *..destaxa..domain..*.*(..))")
	public Object aroundDomainMethod(ProceedingJoinPoint joinPoint) throws Throwable {
		return aroundMethod(joinPoint);
	}

	public Object aroundMethod(ProceedingJoinPoint joinPoint) throws Throwable {
		Tracer tracer = otel.getTracer("br.com.destaxa.v8.colector.vooo", "1.0");
		Signature signature = joinPoint.getSignature();
		Class<?> methodClass = signature.getDeclaringType();
		String methodFullName = methodClass.getSimpleName() + "." + signature.getName();

		Span currentSpan = Span.current();
		Span newSpan = startNewSpan(tracer, methodClass, methodFullName, currentSpan);
		if (Objects.nonNull(newSpan)) {
			try (Scope ignored = newSpan.makeCurrent()) {
				return joinPoint.proceed();
			}
			catch (Throwable throwable) {
				newSpan.setStatus(StatusCode.ERROR, throwable.getMessage());
				newSpan.recordException(throwable);
				throw throwable;
			}
			finally {
				newSpan.end();
			}
		}
		else {
			return joinPoint.proceed();
		}
	}

	private boolean implementsFeignClientInterface(Class<?> clazz) {
		if (clazz.isAnnotationPresent(FeignClient.class)) {
			return true;
		}
		Class<?>[] interfaces = clazz.getInterfaces();
		for (Class<?> anInterface : interfaces) {
			if (anInterface.isAnnotationPresent(FeignClient.class)) {
				return true;
			}
		}
		return false;
	}

	private Span startNewSpan(Tracer tracer, Class<?> methodClass, String methodName, Span currentSpan) {
		Span newSpan;
		if (currentSpan.getSpanContext().isValid()) {
			if (implementsFeignClientInterface(methodClass))
				newSpan = startOptionalSpan(tracer, methodName, SpanKind.CLIENT);
			else
				newSpan = startOptionalSpan(tracer, methodName, SpanKind.INTERNAL);
		}
		else {
			newSpan = tracer.spanBuilder(methodName).setSpanKind(SpanKind.CONSUMER).startSpan();
		}
		return newSpan;
	}

	private Span startOptionalSpan(Tracer tracer, String methodName, SpanKind kind) {
		Span span = null;
		if (enableTraceDepth) {
			span = tracer.spanBuilder(methodName).setSpanKind(kind).startSpan();
		}
		return span;
	}

}