package com.acme.apolice.infrastructure.database.postgresql.apolice.projection;

import com.acme.apolice.adapter.inbound.CategoriaSeguro;
import com.acme.apolice.adapter.inbound.TipoPagamento;
import com.acme.apolice.infrastructure.database.postgresql.apolice.entities.cobertura.CoberturaEntity;
import com.acme.apolice.infrastructure.database.postgresql.apolice.entities.historico.HistoricoEntity;

import java.math.BigDecimal;
import java.time.OffsetDateTime;
import java.util.Set;
import java.util.UUID;

public interface ApoliceConsultaProjection {

    UUID getId();

    UUID getClienteId();

    UUID getProdutoId();

    CategoriaSeguro getCategoria();

    TipoPagamento getMetodoPagemento();

    BigDecimal getValorTotalPremioMensal();

    BigDecimal getValorSegurado();

    CoberturaEntity getCobertura();

    Set<String> getAssistencias();

    OffsetDateTime getDataCriacao();

    OffsetDateTime getDataFinalizacao();

    Set<HistoricoEntity> getHistorico();
}
