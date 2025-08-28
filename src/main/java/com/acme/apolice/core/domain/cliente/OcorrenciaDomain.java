package com.acme.apolice.core.domain.cliente;

import com.acme.apolice.core.domain.enums.TipoCliente;

import java.time.OffsetDateTime;
import java.util.UUID;

public class OcorrenciaDomain {

    private UUID produtoId;
    private TipoCliente tipo;
    private String descricao;
    private OffsetDateTime dataCriacao;
    private OffsetDateTime dataAtualizacao;

    public OcorrenciaDomain() {
    }

    public OcorrenciaDomain(UUID produtoId, TipoCliente tipo, String descricao, OffsetDateTime dataCriacao, OffsetDateTime dataAtualizacao) {
        this.produtoId = produtoId;
        this.tipo = tipo;
        this.descricao = descricao;
        this.dataCriacao = dataCriacao;
        this.dataAtualizacao = dataAtualizacao;
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
}
