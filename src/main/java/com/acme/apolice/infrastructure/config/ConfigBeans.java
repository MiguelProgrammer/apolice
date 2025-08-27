package com.acme.apolice.infrastructure.config;

import com.acme.apolice.adapter.controller.ApoliceController;
import com.acme.apolice.adapter.controller.ClienteController;
import com.acme.apolice.adapter.inbound.ApoliceInMapper;
import com.acme.apolice.adapter.inbound.impl.rest.FraudeRestAdapter;
import com.acme.apolice.adapter.outbound.ApoliceOutMapperDto;
import com.acme.apolice.adapter.outbound.ClienteMapper;
import com.acme.apolice.adapter.outbound.OcorrenciaMapper;
import com.acme.apolice.core.ports.ApoliceRepositoryPort;
import com.acme.apolice.core.ports.driven.kafka.EventPublisher;
import com.acme.apolice.core.usecase.ApoliceUseCase;
import com.acme.apolice.core.usecase.ClienteUseCase;
import com.acme.apolice.core.usecase.EventApoliceUseCase;
import com.acme.apolice.infrastructure.adapter.outbound.ApoliceOutMapperInfra;
import com.acme.apolice.infrastructure.adapter.outbound.CoberturaOutMapperInfra;
import com.acme.apolice.infrastructure.adapter.outbound.HistoricoOutMapperInfra;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestTemplate;

@Configuration
public class ConfigBeans {

    @Bean
    public ApoliceController apoliceController(ApoliceInMapper inMapper, ApoliceOutMapperDto outMapper, ApoliceUseCase apoliceUseCase) {
        return new ApoliceController(inMapper, outMapper, apoliceUseCase);
    }

    @Bean
    public ApoliceUseCase apoliceUseCase(ApoliceOutMapperInfra inMapper,
                                         CoberturaOutMapperInfra coberturaOutMapperInfra,
                                         ApoliceRepositoryPort apoliceAdapter,
                                         HistoricoOutMapperInfra historicoOutMapperInfra) {
        return new ApoliceUseCase(inMapper, apoliceAdapter,
                coberturaOutMapperInfra, historicoOutMapperInfra);
    }

    @Bean
    public ClienteUseCase clienteUseCase(ApoliceOutMapperInfra inMapper,
                                         CoberturaOutMapperInfra coberturaOutMapperInfra,
                                         ApoliceRepositoryPort apoliceAdapter, HistoricoOutMapperInfra historicoOutMapperInfra) {
        return new ClienteUseCase(inMapper, apoliceAdapter, coberturaOutMapperInfra, historicoOutMapperInfra);
    }

    @Bean
    public EventApoliceUseCase eventApoliceUseCase(EventPublisher eventPublisher,
                                                   ObjectMapper objectMapper,
                                                   ClienteMapper clienteMapper,
                                                   ApoliceUseCase apoliceUseCase,
                                                   OcorrenciaMapper ocorrenciaMapper,
                                                   ApoliceRepositoryPort apoliceAdapter,
                                                   ApoliceOutMapperInfra inMapper) {
        return new EventApoliceUseCase(eventPublisher, objectMapper,
                clienteMapper, ocorrenciaMapper, apoliceAdapter, apoliceUseCase, inMapper);
    }

    @Bean
    public RestTemplate restTemplate() {
        return new RestTemplate();
    }

    @Bean
    public FraudeRestAdapter fraudeRestAdapter(ObjectMapper objectMapper, RestTemplate restTemplate,
                                               ClienteController clienteController) {
        return new FraudeRestAdapter(objectMapper, restTemplate, clienteController);
    }
}
