package com.acme.apolice.infrastructure.adapter.outbound;

import com.acme.apolice.adapter.inbound.ApoliceConsulta;
import com.acme.apolice.core.domain.apolice.ApoliceDomain;
import com.acme.apolice.infrastructure.database.postgresql.apolice.entities.apolice.ApoliceEntity;
import com.acme.apolice.infrastructure.database.postgresql.apolice.projection.ApoliceConsultaProjection;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface ApoliceOutMapperInfra {

    /**
     * Domain to Infra<br>
     * Infra to Domain<br>
     * Projection to Domain
     */
    ApoliceEntity domainToEntity(ApoliceDomain apoliceDomain);
    ApoliceDomain entityToDomain(ApoliceEntity dto);
    ApoliceConsulta projectionToDomain(ApoliceConsultaProjection apoliceConsultaProjection);
    ApoliceEntity domainConsultaToEntity(ApoliceConsulta apoliceConsulta);
}