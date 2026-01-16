package com.projectmg.Domain.Dto.OrdemCorte;

import java.util.Map;

public record OrdemCorteItemResponseDTO(

        Long referenciaId,
        String referenciaNome,

        Long materialId,
        String materialNome,

        Map<String, Integer> gradeTamanhos,

        Integer totalPecas,

        Double materialEstimadoKg
) {
}
