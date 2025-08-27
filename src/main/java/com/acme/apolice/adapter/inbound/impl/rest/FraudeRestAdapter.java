package com.acme.apolice.adapter.inbound.impl.rest;

import com.acme.apolice.adapter.controller.ClienteController;
import com.acme.apolice.core.ports.driven.rest.FraudeRestTemplate;
import com.acme.fraude.AnaliseFraudeRequest;
import com.acme.fraude.AnaliseFraudeResponse;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

@Component
public class FraudeRestAdapter implements FraudeRestTemplate {

    private final ObjectMapper objectMapper;
    private final RestTemplate restTemplate;
    private final ClienteController clienteController;

    @Value("${api.fraude.url}")
    private String fraudeApiUrl;

    public FraudeRestAdapter(ObjectMapper objectMapper, RestTemplate restTemplate, ClienteController clienteController) {
        this.objectMapper = objectMapper;
        this.restTemplate = restTemplate;
        this.clienteController = clienteController;
    }

    @Override
    public AnaliseFraudeResponse analisarRisco(AnaliseFraudeRequest analiseRequest) {

        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        HttpEntity<AnaliseFraudeRequest> request = new HttpEntity<>(analiseRequest, headers);

        try {
            AnaliseFraudeResponse analiseFraudeResponse = restTemplate.postForObject(fraudeApiUrl, request, AnaliseFraudeResponse.class);
            ResponseEntity<?> responseEntity = clienteController.analisarRisco(analiseFraudeResponse.getClienteId());
            responseEntity.getBody();
            return analiseFraudeResponse;
        } catch (RuntimeException e) {
            throw new RuntimeException(e);
        }
    }
}
