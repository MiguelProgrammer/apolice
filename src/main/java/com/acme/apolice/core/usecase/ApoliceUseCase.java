package com.acme.apolice.core.usecase;

import com.acme.apolice.adapter.inbound.ApoliceConsulta;
import com.acme.apolice.adapter.inbound.impl.mapper.ApoliceMapperImpl;
import com.acme.apolice.core.domain.apolice.ApoliceDomain;
import com.acme.apolice.core.ports.ApoliceRepositoryPort;
import com.acme.apolice.core.usecase.exception.ApoliceUseCaseBusinessException;
import com.acme.apolice.infrastructure.adapter.outbound.ApoliceOutMapperInfra;
import com.acme.apolice.infrastructure.adapter.outbound.CoberturaOutMapperInfra;
import com.acme.apolice.infrastructure.adapter.outbound.HistoricoOutMapperInfra;
import com.acme.apolice.infrastructure.database.postgresql.apolice.entities.apolice.ApoliceEntity;
import com.acme.apolice.infrastructure.database.postgresql.apolice.projection.ApoliceConsultaProjection;

import java.util.UUID;

public class ApoliceUseCase {

    private final ApoliceOutMapperInfra inMapper;
    private final ApoliceRepositoryPort apoliceAdapter;
    private final ApoliceMapperImpl mapperApolice;

    public ApoliceUseCase(ApoliceOutMapperInfra inMapper, ApoliceRepositoryPort apoliceAdapter, CoberturaOutMapperInfra coberturaOutMapperInfra, HistoricoOutMapperInfra historicoOutMapperInfra) {
        this.inMapper = inMapper;
        this.apoliceAdapter = apoliceAdapter;
        this.mapperApolice = new ApoliceMapperImpl(inMapper, coberturaOutMapperInfra, historicoOutMapperInfra);
    }

    public ApoliceDomain enquadramento(ApoliceDomain domain) {
        try {
            ApoliceEntity entity = mapperApolice.mapperApolice(domain);
            return inMapper.entityToDomain(apoliceAdapter.save(entity));
        } catch (ApoliceUseCaseBusinessException e){
            throw new ApoliceUseCaseBusinessException(e.getMessage());
        }
    }

    public ApoliceConsulta apoliceDetalhada(UUID id) {
        ApoliceConsultaProjection apoliceConsultaProjection = apoliceAdapter.listaApolice(id);
        return mapperApolice.mapperProjection(apoliceConsultaProjection);
    }
}
