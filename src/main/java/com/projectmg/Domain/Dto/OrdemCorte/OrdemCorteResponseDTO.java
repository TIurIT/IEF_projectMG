package com.projectmg.Domain.Dto.OrdemCorte;

import java.time.LocalDateTime;
import java.util.List;

public record OrdemCorteResponseDTO(

        Long id,

        String usuarioCriador,

        LocalDateTime dataCriacao,

        Boolean prontaParaDespacho,

        List<OrdemCorteItemResponseDTO> itens

) {
}