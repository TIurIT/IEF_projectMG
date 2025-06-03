package com.projectmg.Models;

import com.projectmg.Enum.TipoAcao;
import com.projectmg.Security.UsuarioAuditoria;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Objects;

@Entity
@Table(name = "tb_historico_material")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class HistoricoMaterial {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(optional = false)
    @JoinColumn(name = "material_id")
    private Material material;

    @Column(nullable = false)
    private Integer quantidade;

    @Column(nullable = false)
    private String comentario;

    @Column(name = "data_historico", nullable = false)
    private LocalDate dataHistorico;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private TipoAcao acao;

    @Column(name = "usuarioUltimaAlteracao", nullable = false)
    private String usuarioUltimaAlteracao;

    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;
        HistoricoMaterial that = (HistoricoMaterial) o;
        return Objects.equals(id, that.id) && Objects.equals(material, that.material) && Objects.equals(quantidade, that.quantidade) && Objects.equals(comentario, that.comentario) && Objects.equals(dataHistorico, that.dataHistorico) && acao == that.acao && Objects.equals(usuarioUltimaAlteracao, that.usuarioUltimaAlteracao);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, material, quantidade, comentario, dataHistorico, acao, usuarioUltimaAlteracao);
    }
}
