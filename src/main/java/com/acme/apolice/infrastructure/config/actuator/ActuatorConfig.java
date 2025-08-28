package com.acme.apolice.infrastructure.config.actuator;

import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import org.springframework.boot.actuate.health.Health;
import org.springframework.boot.actuate.health.HealthIndicator;
import org.springframework.boot.actuate.info.InfoContributor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ActuatorConfig {

    @PersistenceContext
    private EntityManager entityManager;

    /**
     * Exemplo de InfoContributor para adicionar detalhes sobre a aplicação
     */
    @Bean
    public InfoContributor appInfoContributor() {
        return builder -> {
            builder.withDetail("app", "ACME - Apólices Spring Boot")
                    .withDetail("versao", "1.0.0")
                    .withDetail("endpoints", "/actuator/health, /actuator/info")
                    .withDetail("database", databaseHealth());
        };
    }

    @Bean
    public HealthIndicator databaseHealth() {
        return () -> {
            try {
                // Realiza uma simples consulta para verificar a conectividade com o banco
                entityManager.createQuery("SELECT 1").getSingleResult();
                return Health.up().withDetail("database", "Conectado com sucesso!").build();
            } catch (Exception e) {
                return Health.down().withDetail("database", "Erro ao conectar com o banco: " + e.getMessage()).build();
            }
        };
    }
}
