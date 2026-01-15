package com.projectmg.Domain.Dto.Despacho;

import com.projectmg.Domain.Enum.TipoServico;

public record HistoricoDespachoDTO(
        Long despachoId,
        TipoServico tipoServico,
        Long terceiroId,
        String acao
) {
}