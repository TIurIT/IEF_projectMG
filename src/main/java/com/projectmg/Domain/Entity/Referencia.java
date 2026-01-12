package com.projectmg.Domain.Entity;

import com.projectmg.Domain.Enum.TipoAcao;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.Objects;

@Table(name = "tb_referencia")
@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Referencia {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "nome")
    private String nome;

    @Column(name = "referencia")
    private String referencia;

    @Column(name = "rendimento")
    private Double rendimento;

    @Column(name = "data_atualizacao")
    private LocalDateTime dataAtualizacao;

    @Column(name = "usuario_ultima_alteracao")
    private String usuarioUltimaAlteracao;

    @Enumerated(EnumType.STRING)
    @Column(name= "acao", nullable = false)
    private TipoAcao acao;

    @Column(nullable = false)
    private Boolean ativo = true;

    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;
        Referencia that = (Referencia) o;
        return Objects.equals(id, that.id) && Objects.equals(nome, that.nome) && Objects.equals(referencia, that.referencia) && Objects.equals(rendimento, that.rendimento) && Objects.equals(dataAtualizacao, that.dataAtualizacao) && Objects.equals(usuarioUltimaAlteracao, that.usuarioUltimaAlteracao) && acao == that.acao;
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, nome, referencia, rendimento, dataAtualizacao, usuarioUltimaAlteracao, acao);
    }
}