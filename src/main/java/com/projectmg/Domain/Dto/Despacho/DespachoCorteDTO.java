package com.projectmg.Domain.Dto.Despacho;

public record DespachoCorteDTO(
        Long terceiroId,
        Long referenciaId,
        Long materialId,
        Integer quantidadePecasSolicitadas,
        Double quantidadeMaterialEnviado,
        String usuarioCriacao
) {}