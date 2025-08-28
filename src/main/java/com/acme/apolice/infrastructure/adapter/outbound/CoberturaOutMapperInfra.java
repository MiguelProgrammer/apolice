package com.acme.apolice.infrastructure.adapter.outbound;

import com.acme.apolice.core.domain.cobertura.CoberturaDomain;
import com.acme.apolice.infrastructure.database.postgresql.apolice.entities.cobertura.CoberturaEntity;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface CoberturaOutMapperInfra {

    /**
     * Domain to Infra<br>
     * Infra to Domain
     */
    CoberturaEntity domainToEntity(CoberturaDomain domain);
    CoberturaDomain entityToDomain(CoberturaEntity entity);

}