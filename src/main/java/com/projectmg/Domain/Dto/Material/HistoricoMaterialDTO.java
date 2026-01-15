package com.projectmg.Domain.Dto.Material;

import com.projectmg.Domain.Enum.TipoAcao;
import com.projectmg.Domain.Entity.Material.HistoricoMaterial;


import java.time.LocalDateTime;


public record HistoricoMaterialDTO(
        Long id,
        String nome,
        TipoAcao acao,
        Double quantidadeAlterada,
        String usuarioUltimaAtualizacao,
        String comentario,
        LocalDateTime dataCriacao,
        LocalDateTime dataAtualizacao,
        Long referenciaId,
        Integer quantidadePecasVendidas

) {
    public static HistoricoMaterialDTO fromEntity(HistoricoMaterial h) {
        return new HistoricoMaterialDTO(
                h.getId(),
                h.getMaterial() != null ? h.getMaterial().getNome() : "Material removido",
                h.getAcao(),
                h.getQuantidadeAlterada(),
                h.getUsuarioUltimaAtualizacao(),
                h.getComentario(),
                h.getDataCriacao(),
                h.getDataAtualizacao(),
                h.getReferenciaId(),
                h.getQuantidadePecasVendidas()
        );
    }
}
