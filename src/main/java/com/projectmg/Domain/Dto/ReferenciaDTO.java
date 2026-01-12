package com.projectmg.Domain.Dto;

import com.projectmg.Domain.Enum.TipoAcao;
import java.time.LocalDateTime;

public record ReferenciaDTO(
        Long id,
        String nome,
        String referencia,
        Double rendimento,
        boolean ativo,
        TipoAcao acao,
        String usuarioUltimaAlteracao,
        LocalDateTime dataAtualizacao
) {
}
