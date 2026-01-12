package com.projectmg.Domain.Enum;

public enum StatusDespache {
    ENVIADO("Enviado"),
    RETORNADO("Retornado"),
    PARCIAL("Parcial");

    private final String statusDespache;

    StatusDespache(String statusDespache) { this.statusDespache = statusDespache;}
    public String getStatusDespache() { return statusDespache; }
}
