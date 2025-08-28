package com.acme.apolice.core.domain.historico;

import com.acme.apolice.adapter.inbound.Estado;
import com.acme.apolice.core.domain.apolice.ApoliceDomain;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.FetchType;
import jakarta.persistence.ManyToOne;

import java.time.OffsetDateTime;
import java.util.UUID;

public class HistoricoDomain {

    private UUID historicoId;

    @ManyToOne(fetch = FetchType.LAZY)
    private ApoliceDomain apolice;

    @Enumerated(EnumType.STRING)
    private Estado status;

    private OffsetDateTime timestamp;

    public HistoricoDomain() {
    }

    public HistoricoDomain(UUID historicoId, ApoliceDomain apolice, Estado status, OffsetDateTime timestamp) {
        this.historicoId = historicoId;
        this.apolice = apolice;
        this.status = status;
        this.timestamp = timestamp;
    }

    public UUID getHistoricoId() {
        return historicoId;
    }

    public void setHistoricoId(UUID historicoId) {
        this.historicoId = historicoId;
    }

    public ApoliceDomain getApolice() {
        return apolice;
    }

    public void setApolice(ApoliceDomain apolice) {
        this.apolice = apolice;
    }

    public Estado getStatus() {
        return status;
    }

    public void setStatus(Estado status) {
        this.status = status;
    }

    public OffsetDateTime getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(OffsetDateTime timestamp) {
        this.timestamp = timestamp;
    }
}
