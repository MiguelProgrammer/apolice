package com.acme.apolice.adapter.outbound;

import com.acme.apolice.core.domain.cliente.ClienteDomain;
import com.acme.apolice.core.domain.cliente.OcorrenciaDomain;
import com.acme.apolice.infrastructure.database.postgresql.apolice.entities.cliente.ClienteEntity;
import com.acme.apolice.infrastructure.database.postgresql.apolice.entities.cliente.OcorrenciaEntity;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface OcorrenciaMapper {

    /**
     * Inbound to Domain<br>
     * Domain to Inbound
     */
    OcorrenciaEntity domainToInfra(OcorrenciaDomain domain);
    OcorrenciaDomain infraToDomain(OcorrenciaEntity entity);
}