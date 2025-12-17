package com.projectmg.Dtos;

public record VendaReferenciaDTO(
        Long materialId,
        Long referenciaId,
        Integer quantidadePecas,
        String comentario
) {}
