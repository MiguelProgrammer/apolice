package com.acme.apolice.framework.web.resource;

import com.acme.apolice.adapter.controller.ClienteController;
import com.acme.apolice.adapter.inbound.ApoliceConsulta;
import com.acme.apolice.controller.V1Api;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.UUID;

@RestController
@RequestMapping("/v1/cliente")
public class ClienteResource /*implements V1Api*/ {

    @Autowired
    private ClienteController controller;

    @GetMapping("/{idCliente}/apolice")
    public ResponseEntity<ApoliceConsulta> listaApolicePorCliente(UUID idCliente) {
        return controller.listaApolicePorCliente(idCliente);
    }
}
