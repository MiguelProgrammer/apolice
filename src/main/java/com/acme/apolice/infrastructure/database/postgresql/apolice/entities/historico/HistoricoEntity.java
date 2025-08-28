package com.acme.apolice.infrastructure.database.postgresql.apolice.entities.historico;

import com.acme.apolice.adapter.inbound.Estado;
import com.acme.apolice.infrastructure.database.postgresql.apolice.entities.apolice.ApoliceEntity;
import jakarta.persistence.*;

import java.io.Serial;
import java.io.Serializable;
import java.time.OffsetDateTime;
import java.util.UUID;

@Entity
@Table(name = "historico")
@SequenceGenerator(name = "historico_sequence")
public class HistoricoEntity implements Serializable {

    @Serial
    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private UUID id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "apolice_id")
    private ApoliceEntity apolice;

    @Column(name = "status")
    @Enumerated(EnumType.STRING)
    private Estado status;

    @Column(name = "data_ini")
    private OffsetDateTime dataCriacao;

    public HistoricoEntity() {
    }

    public HistoricoEntity(ApoliceEntity apolice, Estado status, OffsetDateTime dataCriacao) {
        this.apolice = apolice;
        this.status = status;
        this.dataCriacao = dataCriacao;
    }

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public ApoliceEntity getApolice() {
        return apolice;
    }

    public void setApolice(ApoliceEntity apolice) {
        this.apolice = apolice;
    }

    public Estado getStatus() {
        return status;
    }

    public void setStatus(Estado status) {
        this.status = status;
    }

    public OffsetDateTime getDataCriacao() {
        return dataCriacao;
    }

    public void setDataCriacao(OffsetDateTime dataCriacao) {
        this.dataCriacao = dataCriacao;
    }
}
