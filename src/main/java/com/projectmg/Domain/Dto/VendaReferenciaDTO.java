package com.projectmg.Domain.Dto;

public record VendaReferenciaDTO(
        Long materialId,
        Long referenciaId,
        Integer quantidadePecas,
        String comentario
) {}
