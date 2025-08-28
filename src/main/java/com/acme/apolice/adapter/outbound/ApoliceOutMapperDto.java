package com.acme.apolice.adapter.outbound;

import com.acme.apolice.adapter.inbound.ApoliceResponse;
import com.acme.apolice.core.domain.apolice.ApoliceDomain;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface ApoliceOutMapperDto {

    /**
     * Inbound to Domain<br>
     * Domain to Inbound
     */
    ApoliceResponse domainToOutbound(ApoliceDomain apoliceDomain);
}
