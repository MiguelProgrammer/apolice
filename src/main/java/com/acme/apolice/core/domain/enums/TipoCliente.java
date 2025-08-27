package com.acme.apolice.core.domain.enums;

import com.acme.apolice.adapter.inbound.CategoriaSeguro;
import java.math.BigDecimal;
import java.util.List;

public enum TipoCliente {

    /**
     * Cliente Regular<hr>
     * Cliente com um perfil de risco baixo, com histórico comum e sem registros significativos de risco.
     */
    REGULAR("Risco baixo") {

        @Override
        public Boolean validar(BigDecimal valorSegurado, List<CategoriaSeguro> cobertura) {
            if (cobertura.contains(CategoriaSeguro.VIDA) || cobertura.contains(CategoriaSeguro.RESIDENCIAL)) {
                // Regra a: Capital segurado não ultrapasse R$ 500.000,00 para seguro de vida ou residencial
                return valorSegurado.compareTo(new BigDecimal("500000.00")) <= 0;
            }
            if (cobertura.contains(CategoriaSeguro.AUTO)) {
                // Regra b: Capital segurado não ultrapasse R$ 350.000,00 para seguro auto
                return valorSegurado.compareTo(new BigDecimal("350000.00")) <= 0;
            }
            // Regra c: Capital segurado não ultrapasse R$ 255.000,00 para qualquer outro tipo de seguro
            return valorSegurado.compareTo(new BigDecimal("255000.00")) <= 0;
        }
    },

    /**
     * Cliente Alto Risco<hr>
     * Cliente com perfil de maior risco, seja por comportamento ou
     * histórico recente de problemas relacionados a sinistros.
     */
    ALTO_RISCO("Maior Risco") {

        @Override
        public Boolean validar(BigDecimal valorSegurado, List<CategoriaSeguro> cobertura) {
            if (cobertura.contains(CategoriaSeguro.AUTO)) {
                // Regra a: Capital segurado não ultrapasse R$ 250.000,00 para seguro auto
                return valorSegurado.compareTo(new BigDecimal("250000.00")) <= 0;
            }
            if (cobertura.contains(CategoriaSeguro.RESIDENCIAL)) {
                // Regra b: Capital segurado não ultrapasse R$ 150.000,00 para seguro residencial
                return valorSegurado.compareTo(new BigDecimal("150000.00")) <= 0;
            }
            // Regra c: Capital segurado não ultrapasse R$ 125.000,00 para qualquer outro tipo de seguro
            return valorSegurado.compareTo(new BigDecimal("125000.00")) <= 0;
        }
    },

    /**
     * Cliente Preferencial<hr>
     * Cliente com um bom relacionamento com a seguradora.
     */
    PREFERENCIAL("Bom Relacionamento") {
        @Override
        public Boolean validar(BigDecimal valorSegurado, List<CategoriaSeguro> cobertura) {
            if (cobertura.contains(CategoriaSeguro.VIDA)) {
                // Regra a: Capital segurado inferior a R$ 800.000,00 para seguro vida
                return valorSegurado.compareTo(new BigDecimal("800000.00")) < 0; // Menor que
            }
            if (cobertura.contains(CategoriaSeguro.AUTO) || cobertura.contains(CategoriaSeguro.RESIDENCIAL)) {
                // Regra b: Capital segurado inferior a R$ 450.000,00 para seguro auto e residencial
                return valorSegurado.compareTo(new BigDecimal("450000.00")) < 0; // Menor que
            }
            // Regra c: Capital segurado não ultrapasse R$ 375.000,00 para qualquer outro tipo de seguro
            return valorSegurado.compareTo(new BigDecimal("375000.00")) <= 0;
        }
    },

    /**
     * Cliente Sem Informação<hr>
     * Cliente sem histórico ou com pouco histórico com a seguradora.
     */
    SEM_INFORMACAO("Sem informação") {
        @Override
        public Boolean validar(BigDecimal valorSegurado, List<CategoriaSeguro> cobertura) {
            if (cobertura.contains(CategoriaSeguro.VIDA) || cobertura.contains(CategoriaSeguro.RESIDENCIAL)) {
                // Regra a: Capital segurado não ultrapasse R$ 200.000,00 para seguro de vida ou residencial
                return valorSegurado.compareTo(new BigDecimal("200000.00")) <= 0;
            }
            if (cobertura.contains(CategoriaSeguro.AUTO)) {
                // Regra b: Capital segurado não ultrapasse R$ 75.000,00 para seguro auto
                return valorSegurado.compareTo(new BigDecimal("75000.00")) <= 0;
            }
            // Regra c: Capital segurado não ultrapasse R$ 55.000,00 para qualquer outro tipo de seguro
            return valorSegurado.compareTo(new BigDecimal("55000.00")) <= 0;
        }
    };

    TipoCliente(String s) {}

    public abstract Boolean validar(BigDecimal valorSegurado, List<CategoriaSeguro> cobertura);

    /**
     * Determina o tipo de cliente com base no valor segurado e nas categorias de seguro.
     * Retorna a primeira classificação que satisfaça as regras de validação.
     *
     * @param valorSegurado O capital segurado da apólice.
     * @param cobertura A lista de categorias de seguro contratadas.
     * @return O TipoCliente correspondente, ou null se nenhuma regra for satisfeita.
     */
    public static TipoCliente determinarTipo(BigDecimal valorSegurado, List<CategoriaSeguro> cobertura) {
        for (TipoCliente tipo : TipoCliente.values()) {
            if (tipo.validar(valorSegurado, cobertura)) {
                return tipo;
            }
        }
        return null;
    }
}