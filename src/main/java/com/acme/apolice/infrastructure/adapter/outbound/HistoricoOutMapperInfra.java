package com.acme.apolice.infrastructure.adapter.outbound;

import com.acme.apolice.adapter.inbound.Historico;
import com.acme.apolice.core.domain.historico.HistoricoDomain;
import com.acme.apolice.infrastructure.database.postgresql.apolice.entities.historico.HistoricoEntity;
import jakarta.validation.Valid;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring")
public interface HistoricoOutMapperInfra {

    /**
     * Domain to Infra<br>
     * Infra to Domain
     */
    HistoricoDomain domainToEntity(HistoricoDomain entity);

    HistoricoEntity entityToDomain(HistoricoDomain domain);

    List<@Valid Historico> listEnityToDomain(List<HistoricoEntity> historicoOrdenado);
}