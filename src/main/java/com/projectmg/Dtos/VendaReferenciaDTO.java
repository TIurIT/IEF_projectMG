package com.projectmg.Dtos;

public record VendaReferenciaDTO(
        Long materialId,
        Long referenciaId,
        Double rendimentoReferencia,
        Integer quantidadePecas,
        String comentario
) {}
