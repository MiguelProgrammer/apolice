package com.acme.apolice.infrastructure.database.postgresql.apolice.entities.cliente;

import com.acme.apolice.core.domain.enums.TipoCliente;
import jakarta.persistence.*;

import java.io.Serial;
import java.io.Serializable;
import java.time.OffsetDateTime;
import java.util.UUID;

@Entity
@Table(name = "ocorrencia")
@SequenceGenerator(name = "ocorrencia_sequence")
public class OcorrenciaEntity implements Serializable {

    @Serial
    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private UUID id;

    @Column(name = "produto_id")
    private UUID produtoId;

    @Column(name = "tipo")
    private TipoCliente tipo;

    @Column(name = "descricao")
    private String descricao;

    @Column(name = "data_criacao")
    private OffsetDateTime dataCriacao;

    @Column(name = "data_atualizacao")
    private OffsetDateTime dataAtualizacao;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "cliente_id")
    private ClienteEntity cliente;

    public OcorrenciaEntity() {
    }

    public OcorrenciaEntity(UUID id, UUID produtoId, TipoCliente tipo, String descricao, OffsetDateTime dataCriacao, OffsetDateTime dataAtualizacao, ClienteEntity clienteId) {
        this.id = id;
        this.produtoId = produtoId;
        this.tipo = tipo;
        this.descricao = descricao;
        this.dataCriacao = dataCriacao;
        this.dataAtualizacao = dataAtualizacao;
        this.cliente = clienteId;
    }

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public UUID getProdutoId() {
        return produtoId;
    }

    public void setProdutoId(UUID produtoId) {
        this.produtoId = produtoId;
    }

    public TipoCliente getTipo() {
        return tipo;
    }

    public void setTipo(TipoCliente tipo) {
        this.tipo = tipo;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public OffsetDateTime getDataCriacao() {
        return dataCriacao;
    }

    public void setDataCriacao(OffsetDateTime dataCriacao) {
        this.dataCriacao = dataCriacao;
    }

    public OffsetDateTime getDataAtualizacao() {
        return dataAtualizacao;
    }

    public void setDataAtualizacao(OffsetDateTime dataAtualizacao) {
        this.dataAtualizacao = dataAtualizacao;
    }

    public ClienteEntity getCliente() {
        return cliente;
    }

    public void setCliente(ClienteEntity cliente) {
        this.cliente = cliente;
    }
}
