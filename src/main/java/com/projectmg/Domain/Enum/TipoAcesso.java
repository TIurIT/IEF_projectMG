package com.projectmg.Domain.Enum;

public enum TipoAcesso {
    ADMINISTRADOR("Administrador"),
    GERENTE("Gerente"),
    PRODUCAO("Produção"),
    VENDAS("Vendas");

    private final String tipoAcesso;

    TipoAcesso(String tipoAcesso){
        this.tipoAcesso = tipoAcesso;
    }

    public String getTipoAcesso() {
        return tipoAcesso;
    }
}
