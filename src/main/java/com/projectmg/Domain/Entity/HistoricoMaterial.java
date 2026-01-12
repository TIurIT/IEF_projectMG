package com.projectmg.Domain.Entity;

import com.projectmg.Domain.Enum.TipoAcao;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

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

    @Enumerated(EnumType.STRING)
    private TipoAcao acao; // ADICIONADO, RETIRADO, ATUALIZADO

    private Double quantidadeAlterada;

    private String usuarioUltimaAtualizacao;

    @Column(length = 1000)
    private String comentario;

    @ManyToOne (fetch = FetchType.LAZY)
    @JoinColumn(name = "material_id")
    private Material material;

    private LocalDateTime dataCriacao;

    private LocalDateTime dataAtualizacao;

    private Long referenciaId;

    private Integer quantidadePecasVendidas;

    @PrePersist
    public void prePersist() {
        dataCriacao = LocalDateTime.now();
        dataAtualizacao = LocalDateTime.now();
    }

    @PreUpdate
    public void preUpdate() {
        dataAtualizacao = LocalDateTime.now();
    }

    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;
        HistoricoMaterial that = (HistoricoMaterial) o;
        return Objects.equals(id, that.id) && acao == that.acao && Objects.equals(quantidadeAlterada, that.quantidadeAlterada) && Objects.equals(usuarioUltimaAtualizacao, that.usuarioUltimaAtualizacao) && Objects.equals(comentario, that.comentario) && Objects.equals(material, that.material) && Objects.equals(dataCriacao, that.dataCriacao) && Objects.equals(dataAtualizacao, that.dataAtualizacao) && Objects.equals(referenciaId, that.referenciaId) && Objects.equals(quantidadePecasVendidas, that.quantidadePecasVendidas);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, acao, quantidadeAlterada, usuarioUltimaAtualizacao, comentario, material, dataCriacao, dataAtualizacao, referenciaId, quantidadePecasVendidas);
    }
}
