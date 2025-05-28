package com.projectmg.Models;

import com.projectmg.Enum.TipoAcao;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;
import java.util.Objects;

@Table(name = "tb_produto")
@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Produto {

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
        Produto produto = (Produto) o;
        return Objects.equals(id, produto.id) && Objects.equals(nome, produto.nome) && Objects.equals(referencia, produto.referencia) && Objects.equals(dataAtualizacao, produto.dataAtualizacao) && Objects.equals(usuarioUltimaAlteracao, produto.usuarioUltimaAlteracao) && acao == produto.acao;
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, nome, referencia, dataAtualizacao, usuarioUltimaAlteracao, acao);
    }
}
