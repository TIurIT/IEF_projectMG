package com.projectmg.Enum;

public enum Servico {
    COSTUREIRA("Costureira"),
    CORTADOR("Cortador"),
    SUBLIMACAO("Sublimação"),
    ESTAMPARIA("Estamparia");

    private final String servico;

    Servico(String servico){
        this.servico = servico;
    }

    public String getServico() {
        return servico;
    }
}
