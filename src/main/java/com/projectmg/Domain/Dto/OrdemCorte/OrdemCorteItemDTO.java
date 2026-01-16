package com.projectmg.Domain.Dto.OrdemCorte;

import java.util.Map;

public record OrdemCorteItemDTO(

        Long referenciaId,
        Long materialId,
        Map<String, Integer> gradeTamanhos

) {
}
