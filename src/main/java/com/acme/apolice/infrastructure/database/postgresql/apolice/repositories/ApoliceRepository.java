package com.acme.apolice.infrastructure.database.postgresql.apolice.repositories;

import com.acme.apolice.infrastructure.database.postgresql.apolice.entities.apolice.ApoliceEntity;
import com.acme.apolice.infrastructure.database.postgresql.apolice.projection.ApoliceConsultaProjection;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.UUID;

public interface ApoliceRepository extends JpaRepository<ApoliceEntity, UUID> {

    @Query("select a from ApoliceEntity a where a.apoliceId = :id or a.clienteId = :id")
    ApoliceConsultaProjection findByApolice(@Param("id") UUID id);
}
