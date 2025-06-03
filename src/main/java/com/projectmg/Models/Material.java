package com.projectmg.Models;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.projectmg.Enum.TipoAcao;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;
import java.util.Objects;

@Table(name = "tb_estoque_material")
@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Material {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "tipo")
    private String tipo;

    @Column(name = "nome")
    private String nome;

    @Column(name = "marca")
    private String marca;

    @Column(name = "quantidade")
    private Integer quantidade;

    @Column(name = "data_de_criacao")
    @JsonFormat(pattern = "yyyy-MM-dd")
    private LocalDate dataDeCriacao;

    @Column(name = "data_atualizacao")
    private LocalDate dataAtualizacao;

    @Column(name = "comentario")
    private String comentario;

    @Column(name = "usuario_ultima_alteracao")
    private String usuarioUltimaAlteracao;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private TipoAcao acao;

    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;
        Material material = (Material) o;
        return Objects.equals(id, material.id) && Objects.equals(tipo, material.tipo) && Objects.equals(nome, material.nome) && Objects.equals(marca, material.marca) && Objects.equals(quantidade, material.quantidade) && Objects.equals(dataDeCriacao, material.dataDeCriacao) && Objects.equals(dataAtualizacao, material.dataAtualizacao) && Objects.equals(comentario, material.comentario) && Objects.equals(usuarioUltimaAlteracao, material.usuarioUltimaAlteracao) && acao == material.acao;
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, tipo, nome, marca, quantidade, dataDeCriacao, dataAtualizacao, comentario, usuarioUltimaAlteracao, acao);
    }
}
