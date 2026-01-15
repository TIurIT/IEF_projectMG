package com.projectmg.Domain.Dto.Material;

public record VendaReferenciaDTO(
        Long materialId,
        Long referenciaId,
        Integer quantidadePecas,
        String comentario
) {}
