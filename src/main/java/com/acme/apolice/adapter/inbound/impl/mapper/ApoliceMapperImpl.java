package com.acme.apolice.adapter.inbound.impl.mapper;

import com.acme.apolice.adapter.exception.AdapterException;
import com.acme.apolice.adapter.inbound.ApoliceConsulta;
import com.acme.apolice.adapter.inbound.Estado;
import com.acme.apolice.core.domain.apolice.ApoliceDomain;
import com.acme.apolice.infrastructure.adapter.outbound.ApoliceOutMapperInfra;
import com.acme.apolice.infrastructure.adapter.outbound.CoberturaOutMapperInfra;
import com.acme.apolice.infrastructure.adapter.outbound.HistoricoOutMapperInfra;
import com.acme.apolice.infrastructure.database.postgresql.apolice.entities.apolice.ApoliceEntity;
import com.acme.apolice.infrastructure.database.postgresql.apolice.entities.cobertura.CoberturaEntity;
import com.acme.apolice.infrastructure.database.postgresql.apolice.entities.historico.HistoricoEntity;
import com.acme.apolice.infrastructure.database.postgresql.apolice.projection.ApoliceConsultaProjection;

import java.util.Comparator;
import java.util.List;
import java.util.Set;

public record ApoliceMapperImpl(ApoliceOutMapperInfra inMapper,
                                CoberturaOutMapperInfra coberturaOutMapperInfra,
                                HistoricoOutMapperInfra historicoOutMapperInfra) {

    /**
     * INTERNAL SERVICE | MAPPER
     */
    public ApoliceEntity mapperApolice(ApoliceDomain apoliceDomain) {

        try {
            ApoliceEntity apolice = inMapper.domainToEntity(apoliceDomain);
            CoberturaEntity cobertura = coberturaOutMapperInfra.domainToEntity(apoliceDomain.getCobertura());
            apolice.setHistorico(Set.of(new HistoricoEntity(apolice, Estado.RECEBIDO, apolice.getDataCriacao())));
            apolice.setCobertura(cobertura);
            return apolice;
        } catch (NullPointerException e) {
            throw new AdapterException(e.getMessage());
        }
    }

    /**
     * INTERNAL SERVICE | MAPPER
     */
    public ApoliceConsulta mapperProjection(ApoliceConsultaProjection apoliceConsultaProjections) {

        try {
            ApoliceConsulta dto = inMapper.projectionToDomain(apoliceConsultaProjections);
            List<HistoricoEntity> historicoOrdenado = apoliceConsultaProjections.getHistorico().stream()
                    .sorted(Comparator.comparing(HistoricoEntity::getDataCriacao))
                    .toList();
            dto.setHistorico(historicoOutMapperInfra.listEnityToDomain(historicoOrdenado));
            if (!historicoOrdenado.isEmpty()) {
                dto.setStatus(historicoOrdenado.get(historicoOrdenado.size() - 1).getStatus());
            }
            return dto;
        } catch (NullPointerException e){
            throw new AdapterException(e.getMessage());
        }
    }

}
