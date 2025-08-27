package com.acme.apolice.core.usecase;

import com.acme.apolice.adapter.inbound.ApoliceConsulta;
import com.acme.apolice.adapter.inbound.impl.mapper.ApoliceMapperImpl;
import com.acme.apolice.core.ports.ApoliceRepositoryPort;
import com.acme.apolice.infrastructure.adapter.outbound.ApoliceOutMapperInfra;
import com.acme.apolice.infrastructure.adapter.outbound.CoberturaOutMapperInfra;
import com.acme.apolice.infrastructure.adapter.outbound.HistoricoOutMapperInfra;

import java.util.UUID;

public class ClienteUseCase {

    private final ApoliceRepositoryPort apoliceAdapter;
    private final ApoliceMapperImpl mapperApolice;

    public ClienteUseCase(ApoliceOutMapperInfra inMapper, ApoliceRepositoryPort apoliceAdapter, CoberturaOutMapperInfra coberturaOutMapperInfra, HistoricoOutMapperInfra historicoOutMapperInfra) {
        this.apoliceAdapter = apoliceAdapter;
        this.mapperApolice = new ApoliceMapperImpl(inMapper, coberturaOutMapperInfra, historicoOutMapperInfra);
    }

    public ApoliceConsulta listaApolicePorCliente(UUID idCliente) {
        return mapperApolice.mapperProjection(apoliceAdapter.listaApolice(idCliente));
    }
}
