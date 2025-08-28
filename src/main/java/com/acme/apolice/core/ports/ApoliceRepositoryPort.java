package com.acme.apolice.core.ports;

import com.acme.apolice.infrastructure.database.postgresql.apolice.entities.apolice.ApoliceEntity;
import com.acme.apolice.infrastructure.database.postgresql.apolice.projection.ApoliceConsultaProjection;

import java.util.UUID;

public interface ApoliceRepositoryPort {

    ApoliceEntity save(ApoliceEntity apoliceEntity);
    ApoliceConsultaProjection listaApolice(UUID id);
}
