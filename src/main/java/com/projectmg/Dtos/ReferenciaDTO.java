package com.projectmg.Dtos;

import com.projectmg.Enum.TipoAcao;
import com.projectmg.Configuration.UsuarioAuditoria;
import jakarta.persistence.PrePersist;
import jakarta.persistence.PreUpdate;
import java.time.LocalDate;

public record ReferenciaDTO(
        Long id,
        String nome,
        String referencia,
        Double rendimento,
        LocalDate dataAtualizacao,
        String usuarioUltimaAlteracao,
        TipoAcao acao
) {
    @PrePersist
    @PreUpdate
    public ReferenciaDTO atualizarDataAtualizacao() {
        return new ReferenciaDTO(
                this.id,
                this.nome,
                this.referencia,
                this.rendimento,
                LocalDate.now(),
                UsuarioAuditoria.getUsuarioLogado(),
                this.acao
        );
    }
}