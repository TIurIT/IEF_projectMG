package com.projectmg.Dto;

import com.projectmg.Enum.TipoAcao;
import com.projectmg.Models.HistoricoMaterial;
import com.projectmg.Models.Material;


import java.time.LocalDateTime;
import java.util.Comparator;

public record MaterialDTO(
        Long id,
        String nome,
        String tipo,
        String fornecedor,
        Integer quantidade,
        LocalDateTime dataDeCriacao,
        LocalDateTime dataAtualizacao,
        TipoAcao acao,
        String usuarioUltimaAlteracao,
        String ultimoComentario,
        boolean ativo
) {

    public static MaterialDTO fromEntity(Material m) {
        String ultimoComentario = null;
        if (m.getHistoricos() != null && !m.getHistoricos().isEmpty()) {
            HistoricoMaterial ultimo = m.getHistoricos()
                    .stream()
                    .max(Comparator.comparing(HistoricoMaterial::getDataAtualizacao))
                    .orElse(null);
            if (ultimo != null) {
                ultimoComentario = ultimo.getComentario();
            }
        }
        return new MaterialDTO(
                m.getId(),
                m.getNome(),
                m.getTipo(),
                m.getFornecedor(),
                m.getQuantidade(),
                m.getDataDeCriacao(),
                m.getDataAtualizacao(),
                m.getAcao(),
                m.getUsuarioUltimaAlteracao(),
                ultimoComentario,
                m.isAtivo()
        );
    }

}
