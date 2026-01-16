package com.projectmg.Domain.Enum;

public enum StatusOrdemCorte {
    CRIADA,                 // Criada pelo usuário A
    AGUARDANDO_DESPACHO,    // Conferida e pronta para envio
    PARCIALMENTE_DESPACHADA,
    CONCLUIDA,
    CANCELADA
}
