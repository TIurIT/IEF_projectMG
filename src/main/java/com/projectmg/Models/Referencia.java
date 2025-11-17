package com.projectmg.Models;

import com.projectmg.Enum.TipoAcao;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;
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

    @Column(name = "data_atualizacao")
    private LocalDate dataAtualizacao;

    @Column(name = "usuario_ultima_alteracao")
    private String usuarioUltimaAlteracao;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private TipoAcao acao;


    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;
        Referencia referencia = (Referencia) o;
        return Objects.equals(id, referencia.id) && Objects.equals(nome, referencia.nome) && Objects.equals(this.referencia, referencia.referencia) && Objects.equals(dataAtualizacao, referencia.dataAtualizacao) && Objects.equals(usuarioUltimaAlteracao, referencia.usuarioUltimaAlteracao) && acao == referencia.acao;
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, nome, referencia, dataAtualizacao, usuarioUltimaAlteracao, acao);
    }
}
