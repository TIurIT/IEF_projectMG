package com.projectmg.Dtos;

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
        Integer limiteMinimo,
        boolean ativo
) {

    public static MaterialDTO fromEntity(Material m) {
        HistoricoMaterial ultimoHistorico = null;
        if (m.getHistoricos() != null && !m.getHistoricos().isEmpty()) {
            ultimoHistorico = m.getHistoricos()
                    .stream()
                    .max(Comparator.comparing(h -> h.getDataAtualizacao() != null ? h.getDataAtualizacao() : h.getDataCriacao()))
                    .orElse(null);
        }

        String ultimoComentario = ultimoHistorico != null ? ultimoHistorico.getComentario() : null;
        TipoAcao acao = ultimoHistorico != null ? ultimoHistorico.getAcao() : null;
        String usuarioUltimaAlteracao = ultimoHistorico != null ? ultimoHistorico.getUsuarioUltimaAtualizacao() : null;

        return new MaterialDTO(
                m.getId(),
                m.getNome(),
                m.getTipo(),
                m.getFornecedor(),
                m.getQuantidade(),
                m.getDataDeCriacao(),
                m.getDataAtualizacao(),
                acao,
                usuarioUltimaAlteracao,
                ultimoComentario,
                m.getLimiteMinimo(),
                m.isAtivo()
        );
    }

}
