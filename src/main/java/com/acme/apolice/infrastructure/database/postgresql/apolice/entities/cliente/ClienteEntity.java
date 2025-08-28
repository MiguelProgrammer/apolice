package com.acme.apolice.infrastructure.database.postgresql.apolice.entities.cliente;

import com.acme.apolice.core.domain.enums.TipoCliente;
import jakarta.persistence.*;

import java.io.Serial;
import java.io.Serializable;
import java.time.OffsetDateTime;
import java.util.Set;
import java.util.UUID;

@Entity
@Table(name = "cliente")
@SequenceGenerator(name = "cliente_sequence")
public class ClienteEntity implements Serializable {

    @Serial
    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private UUID id;

    @Column(name = "apolice_id")
    private UUID apoliceId;

    @Column(name = "cliente_id")
    private UUID clienteId;

    @Column(name = "data_analise")
    private OffsetDateTime dataAnalise;

    @Column(name = "classificacao")
    private TipoCliente classificacao;

    @OneToMany(mappedBy = "cliente", fetch = FetchType.EAGER, cascade = CascadeType.ALL, orphanRemoval = true)
    private Set<OcorrenciaEntity> occurrencias;

    public ClienteEntity() {
    }

    public ClienteEntity(UUID id, UUID apoliceId, UUID clienteId, OffsetDateTime dataAnalise, TipoCliente classificacao, Set<OcorrenciaEntity> occurrencias) {
        this.id = id;
        this.apoliceId = apoliceId;
        this.clienteId = clienteId;
        this.dataAnalise = dataAnalise;
        this.classificacao = classificacao;
        this.occurrencias = occurrencias;
    }

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
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

    public Set<OcorrenciaEntity> getOccurrencias() {
        return occurrencias;
    }

    public void setOccurrencias(Set<OcorrenciaEntity> occurrencias) {
        this.occurrencias = occurrencias;
    }
}
