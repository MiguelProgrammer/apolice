package com.acme.apolice.infrastructure.database.postgresql.apolice.repositories;

import com.acme.apolice.infrastructure.database.postgresql.apolice.entities.cliente.ClienteEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface ClienteRepository extends JpaRepository<ClienteEntity, UUID> {

}
