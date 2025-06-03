package com.projectmg.Models;

import com.projectmg.Enum.TipoAcao;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
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

    @Column(nullable = false)
    private Integer quantidadeAlterada;

    @Column(nullable = false)
    private String comentario;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private TipoAcao acao;

    @ManyToOne
    @JoinColumn(name = "material_id", nullable = false)
    private Material material;

    @OneToMany(mappedBy = "historico", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Comentario> comentarios = new ArrayList<>();

    @Column(name = "data_historico", nullable = false)
    private LocalDate dataHistorico;

    @Column(name = "usuarioUltimaAlteracao", nullable = false)
    private String usuarioUltimaAlteracao;

    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;
        HistoricoMaterial that = (HistoricoMaterial) o;
        return Objects.equals(id, that.id) && Objects.equals(material, that.material) && Objects.equals(quantidadeAlterada, that.quantidadeAlterada) && Objects.equals(comentario, that.comentario) && Objects.equals(dataHistorico, that.dataHistorico) && acao == that.acao && Objects.equals(usuarioUltimaAlteracao, that.usuarioUltimaAlteracao);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, material, quantidadeAlterada, comentario, dataHistorico, acao, usuarioUltimaAlteracao);
    }
}
