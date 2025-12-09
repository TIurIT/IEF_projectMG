package com.projectmg.Enum;

public enum TipoServico {
    COSTURA("Costura"),
    CORTE("Corte"),
    ESTAMPARIA("Estamparia");

    private final String tipoServico;

    TipoServico(String tipoServico) { this.tipoServico = tipoServico; }
    public String getTipoServico() { return tipoServico; }

}
