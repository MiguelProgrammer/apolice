package com.acme.apolice.adapter.inbound;

import com.acme.apolice.core.domain.cobertura.CoberturaDomain;
import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;

@Mapper(componentModel = "spring")
public interface CoberturaInMapper {

    /**
     * Inbound to Domain<br>
     * Domain to Inbound
     */
    CoberturaDomain inboundToDomain(Cobertura dto);
    Cobertura domainToInbound(CoberturaDomain coberturaDomain);
    void updateDomainFromInbound(Cobertura dto, @MappingTarget CoberturaDomain domain);

}