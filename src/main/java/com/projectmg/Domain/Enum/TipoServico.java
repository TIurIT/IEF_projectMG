package com.projectmg.Domain.Enum;

public enum TipoServico {
    COSTURA("Costura"),
    CORTE("Corte"),
    ESTAMPARIA("Estamparia"),
    SUBLIMACAO("Sublimação");

    private final String tipoServico;

    TipoServico(String tipoServico) { this.tipoServico = tipoServico; }
    public String getTipoServico() { return tipoServico; }

}
