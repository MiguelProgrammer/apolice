package com.acme.apolice.adapter.controller;

import com.acme.apolice.adapter.inbound.Apolice;
import com.acme.apolice.adapter.inbound.ApoliceConsulta;
import com.acme.apolice.adapter.inbound.ApoliceInMapper;
import com.acme.apolice.adapter.inbound.ApoliceResponse;
import com.acme.apolice.adapter.outbound.ApoliceOutMapperDto;
import com.acme.apolice.core.domain.apolice.ApoliceDomain;
import com.acme.apolice.core.usecase.ApoliceUseCase;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;

import java.util.UUID;

@Component
public class ApoliceController {

    private final ApoliceInMapper inMapper;
    private final ApoliceOutMapperDto outMapper;
    private final ApoliceUseCase apoliceUseCase;

    public ApoliceController(ApoliceInMapper inMapper, ApoliceOutMapperDto outMapper, ApoliceUseCase apoliceUseCase) {
        this.inMapper = inMapper;
        this.outMapper = outMapper;
        this.apoliceUseCase = apoliceUseCase;
    }

    public ResponseEntity<ApoliceResponse> geraApolice(Apolice apolice) {
        ApoliceDomain apoliceDomain = apoliceUseCase.enquadramento(inMapper.inboundToDomain(apolice));
        return new ResponseEntity<>(outMapper.domainToOutbound(apoliceDomain), HttpStatus.CREATED);
    }

    public ResponseEntity<ApoliceConsulta> listaApolice(UUID id) {
        return ResponseEntity.ok(apoliceUseCase.apoliceDetalhada(id));
    }
}
