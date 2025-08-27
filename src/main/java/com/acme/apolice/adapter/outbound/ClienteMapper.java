package com.acme.apolice.adapter.outbound;

import com.acme.apolice.core.domain.cliente.ClienteDomain;
import com.acme.apolice.infrastructure.database.postgresql.apolice.entities.cliente.ClienteEntity;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface ClienteMapper {

    /**
     * Inbound to Domain<br>
     * Domain to Inbound
     */
    ClienteEntity domainToInfra(ClienteDomain domain);
    ClienteDomain infraToDomain(ClienteEntity entity);
}