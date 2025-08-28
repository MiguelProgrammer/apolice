package com.acme.apolice.adapter.inbound;

import com.acme.apolice.core.domain.apolice.ApoliceDomain;
import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;

@Mapper(componentModel = "spring", uses = CoberturaInMapper.class)
public interface ApoliceInMapper {

    /**
     * Inbound to Domain<br>
     * Domain to Inbound
     */
    ApoliceDomain inboundToDomain(Apolice dto);

    Apolice domainToInbound(ApoliceDomain apoliceDomain);

    void updateDomainFromInbound(Apolice dto, @MappingTarget ApoliceDomain domain);
}