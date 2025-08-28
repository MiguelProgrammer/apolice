package com.acme.apolice.core.domain.apolice;

import com.acme.apolice.adapter.inbound.CategoriaSeguro;
import com.acme.apolice.adapter.inbound.TipoCanalVendas;
import com.acme.apolice.adapter.inbound.TipoPagamento;
import com.acme.apolice.core.domain.cobertura.CoberturaDomain;
import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.validation.constraints.Digits;

import java.math.BigDecimal;
import java.time.OffsetDateTime;
import java.util.Set;
import java.util.UUID;

public class ApoliceDomain {

    private UUID id;
    private UUID clienteId;
    private UUID produtoId;
    private CategoriaSeguro categoria;
    private TipoCanalVendas origem;
    private TipoPagamento metodoPagemento;
    @Digits(integer = 12, fraction = 2)
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "#0.00")
    private BigDecimal valorTotalPremioMensal;
    @Digits(integer = 12, fraction = 2)
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "#0.00")
    private BigDecimal valorSegurado;
    private CoberturaDomain cobertura;
    private Set<String> assistencias;
    private OffsetDateTime dataCriacao = OffsetDateTime.now();

    public ApoliceDomain() {
    }

    public ApoliceDomain(UUID id, UUID clienteId, UUID produtoId, CategoriaSeguro categoria, TipoCanalVendas origem, TipoPagamento metodoPagemento, BigDecimal valorTotalPremioMensal, BigDecimal valorSegurado, CoberturaDomain cobertura, Set<String> assistencias, OffsetDateTime dataCriacao) {
        this.id = id;
        this.clienteId = clienteId;
        this.produtoId = produtoId;
        this.categoria = categoria;
        this.origem = origem;
        this.metodoPagemento = metodoPagemento;
        this.valorTotalPremioMensal = valorTotalPremioMensal;
        this.valorSegurado = valorSegurado;
        this.cobertura = cobertura;
        this.assistencias = assistencias;
        this.dataCriacao = dataCriacao;
    }

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public UUID getClienteId() {
        return clienteId;
    }

    public void setClienteId(UUID clienteId) {
        this.clienteId = clienteId;
    }

    public UUID getProdutoId() {
        return produtoId;
    }

    public void setProdutoId(UUID produtoId) {
        this.produtoId = produtoId;
    }

    public CategoriaSeguro getCategoria() {
        return categoria;
    }

    public void setCategoria(CategoriaSeguro categoria) {
        this.categoria = categoria;
    }

    public TipoCanalVendas getOrigem() {
        return origem;
    }

    public void setOrigem(TipoCanalVendas origem) {
        this.origem = origem;
    }

    public TipoPagamento getMetodoPagemento() {
        return metodoPagemento;
    }

    public void setMetodoPagemento(TipoPagamento metodoPagemento) {
        this.metodoPagemento = metodoPagemento;
    }

    public BigDecimal getValorTotalPremioMensal() {
        return valorTotalPremioMensal;
    }

    public void setValorTotalPremioMensal(BigDecimal valorTotalPremioMensal) {
        this.valorTotalPremioMensal = valorTotalPremioMensal;
    }

    public BigDecimal getValorSegurado() {
        return valorSegurado;
    }

    public void setValorSegurado(BigDecimal valorSegurado) {
        this.valorSegurado = valorSegurado;
    }

    public CoberturaDomain getCobertura() {
        return cobertura;
    }

    public void setCobertura(CoberturaDomain cobertura) {
        this.cobertura = cobertura;
    }

    public Set<String> getAssistencias() {
        return assistencias;
    }

    public void setAssistencias(Set<String> assistencias) {
        this.assistencias = assistencias;
    }

    public OffsetDateTime getDataCriacao() {
        return dataCriacao;
    }

    public void setDataCriacao(OffsetDateTime dataCriacao) {
        this.dataCriacao = dataCriacao;
    }
}
