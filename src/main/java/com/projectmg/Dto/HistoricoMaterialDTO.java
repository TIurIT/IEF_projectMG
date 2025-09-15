package com.projectmg.Dto;

import com.projectmg.Enum.TipoAcao;
import com.projectmg.Models.HistoricoMaterial;


import java.time.LocalDateTime;


public record HistoricoMaterialDTO(
        Long id,
        String nome,
        TipoAcao acao,
        Integer quantidadeAlterada,
        String usuarioUltimaAtualizacao,
        String comentario,
        LocalDateTime dataCriacao,
        LocalDateTime dataAtualizacao
) {
    public static HistoricoMaterialDTO fromEntity(HistoricoMaterial h) {
        return new HistoricoMaterialDTO(
                h.getId(),
                h.getMaterial() != null ? h.getMaterial().getNome() : "Material removido",
                h.getAcao(),
                h.getQuantidadeAlterada(),
                h.getUsuarioUltimaAtualizacao(),                h.getComentario(),
                h.getDataCriacao(),
                h.getDataAtualizacao()
        );
    }
}
