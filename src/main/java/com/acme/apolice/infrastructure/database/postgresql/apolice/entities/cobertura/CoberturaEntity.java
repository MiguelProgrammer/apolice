package com.acme.apolice.infrastructure.database.postgresql.apolice.entities.cobertura;

import jakarta.persistence.*;

import java.io.Serial;
import java.io.Serializable;
import java.math.BigDecimal;
import java.util.UUID;

@Entity
@Table(name = "cobertura")
@SequenceGenerator(name = "cobertura_sequence")
public class CoberturaEntity implements Serializable {

    @Serial
    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private UUID id;

    @Column(name = "roubo", precision = 12, scale = 2)
    private BigDecimal roubo;

    @Column(name = "perda_total", precision = 12, scale = 2)
    private BigDecimal perdaTotal;

    @Column(name = "colisao_terceiros", precision = 12, scale = 2)
    private BigDecimal colisaoTerceiros;

    public CoberturaEntity() {
    }

    public CoberturaEntity(BigDecimal roubo, BigDecimal perdaTotal, BigDecimal colisaoTerceiros) {
        this.roubo = roubo;
        this.perdaTotal = perdaTotal;
        this.colisaoTerceiros = colisaoTerceiros;
    }

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public BigDecimal getRoubo() {
        return roubo;
    }

    public void setRoubo(BigDecimal roubo) {
        this.roubo = roubo;
    }

    public BigDecimal getPerdaTotal() {
        return perdaTotal;
    }

    public void setPerdaTotal(BigDecimal perdaTotal) {
        this.perdaTotal = perdaTotal;
    }

    public BigDecimal getColisaoTerceiros() {
        return colisaoTerceiros;
    }

    public void setColisaoTerceiros(BigDecimal colisaoTerceiros) {
        this.colisaoTerceiros = colisaoTerceiros;
    }
}
