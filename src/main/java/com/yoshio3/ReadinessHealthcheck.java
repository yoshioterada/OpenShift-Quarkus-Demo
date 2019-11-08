package com.yoshio3;

import javax.enterprise.context.ApplicationScoped;

import org.eclipse.microprofile.health.HealthCheck;
import org.eclipse.microprofile.health.HealthCheckResponse;
import org.eclipse.microprofile.health.Readiness;

@ApplicationScoped
@Readiness
public class ReadinessHealthcheck implements HealthCheck {
 
    @Override
    public HealthCheckResponse call() {
        return HealthCheckResponse.named("Employee Health Check").up().build();
    }
 
}
