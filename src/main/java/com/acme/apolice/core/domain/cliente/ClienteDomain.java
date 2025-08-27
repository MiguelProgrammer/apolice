package com.acme.apolice.core.domain.cliente;

import com.acme.apolice.core.domain.enums.TipoCliente;

import java.time.OffsetDateTime;
import java.util.HashSet;
import java.util.Set;
import java.util.UUID;

public class ClienteDomain {

    private UUID apoliceId;
    private UUID clienteId;
    private OffsetDateTime dataAnalise;
    private TipoCliente classificacao;
    private Set<OcorrenciaDomain> occurrencias = new HashSet<>();

    public ClienteDomain() {
    }

    public ClienteDomain(UUID apoliceId, UUID clienteId, OffsetDateTime dataAnalise, TipoCliente classificacao, Set<OcorrenciaDomain> occurrencias) {
        this.apoliceId = apoliceId;
        this.clienteId = clienteId;
        this.dataAnalise = dataAnalise;
        this.classificacao = classificacao;
        this.occurrencias = occurrencias;
    }

    public UUID getApoliceId() {
        return apoliceId;
    }

    public void setApoliceId(UUID apoliceId) {
        this.apoliceId = apoliceId;
    }

    public UUID getClienteId() {
        return clienteId;
    }

    public void setClienteId(UUID clienteId) {
        this.clienteId = clienteId;
    }

    public OffsetDateTime getDataAnalise() {
        return dataAnalise;
    }

    public void setDataAnalise(OffsetDateTime dataAnalise) {
        this.dataAnalise = dataAnalise;
    }

    public TipoCliente getClassificacao() {
        return classificacao;
    }

    public void setClassificacao(TipoCliente classificacao) {
        this.classificacao = classificacao;
    }

    public Set<OcorrenciaDomain> getOccurrencias() {
        return occurrencias;
    }

    public void setOccurrencias(Set<OcorrenciaDomain> occurrencias) {
        this.occurrencias = occurrencias;
    }
}
