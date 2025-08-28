package com.acme.apolice.adapter.outbound.impl;

import com.acme.apolice.adapter.inbound.Estado;

import java.time.OffsetDateTime;
import java.util.UUID;

public class ApoliceEventDto {

    private UUID apoliceId;
    private Estado estado;
    private OffsetDateTime dataSolicitacao;

    public ApoliceEventDto() {
    }

    public ApoliceEventDto(UUID apoliceId, Estado estado, OffsetDateTime dataSolicitacao) {
        this.apoliceId = apoliceId;
        this.estado = estado;
        this.dataSolicitacao = dataSolicitacao;
    }

    public UUID getApoliceId() {
        return apoliceId;
    }

    public void setApoliceId(UUID apoliceId) {
        this.apoliceId = apoliceId;
    }

    public Estado getEstado() {
        return estado;
    }

    public void setEstado(Estado estado) {
        this.estado = estado;
    }

    public OffsetDateTime getDataSolicitacao() {
        return dataSolicitacao;
    }

    public void setDataSolicitacao(OffsetDateTime dataSolicitacao) {
        this.dataSolicitacao = dataSolicitacao;
    }
}
