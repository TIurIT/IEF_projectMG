package com.projectmg.Domain.Dto.OrdemCorte;

import java.util.List;

public record OrdemCorteCriacaoDTO(

        List<OrdemCorteItemDTO> itens

) {
}