package com.acme.apolice.core.domain.cobertura;

import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.validation.constraints.Digits;

import java.math.BigDecimal;

public class CoberturaDomain {

    @Digits(integer = 12, fraction = 2)
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "#0.00")
    private BigDecimal roubo;

    @Digits(integer = 12, fraction = 2)
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "#0.00")
    private BigDecimal perdaTotal;

    @Digits(integer = 12, fraction = 2)
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "#0.00")
    private BigDecimal colisaoTerceiros;

    public CoberturaDomain() {
    }

    public CoberturaDomain(BigDecimal roubo, BigDecimal perdaTotal, BigDecimal colisaoTerceiros) {
        this.roubo = roubo;
        this.perdaTotal = perdaTotal;
        this.colisaoTerceiros = colisaoTerceiros;
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
