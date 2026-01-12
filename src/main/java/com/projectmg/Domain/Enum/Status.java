package com.projectmg.Domain.Enum;

public enum Status {
    CONCLUIDO("Concluído"),
    ANDAMENTO("Em Andamento"),
    RETRABALHO("Retrabalho"),
    FILA("Em Fila de Produção");

    private final String status;

    Status(String status) { this.status = status; }
    public String getStatus() { return status; }
}
