package com.acme.apolice.adapter.controller;

import com.acme.apolice.adapter.inbound.ApoliceConsulta;
import com.acme.apolice.core.usecase.ClienteUseCase;
import com.acme.apolice.core.usecase.EventApoliceUseCase;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;

import java.util.UUID;

@Component
public class ClienteController {

    private final ClienteUseCase clienteUseCase;
    private final EventApoliceUseCase eventApoliceUseCase;

    public ClienteController(ClienteUseCase clienteUseCase, EventApoliceUseCase eventApoliceUseCase) {
        this.clienteUseCase = clienteUseCase;
        this.eventApoliceUseCase = eventApoliceUseCase;
    }

    public ResponseEntity<ApoliceConsulta> listaApolicePorCliente(UUID idCliente) {
        return ResponseEntity.ok(clienteUseCase.listaApolicePorCliente(idCliente));
    }

    public ResponseEntity<?> analisarRisco(UUID idCliente) {
        ApoliceConsulta apoliceConsulta = clienteUseCase.listaApolicePorCliente(idCliente);
        eventApoliceUseCase.processarApolice(apoliceConsulta);
        return ResponseEntity.ok(apoliceConsulta);
    }
}
