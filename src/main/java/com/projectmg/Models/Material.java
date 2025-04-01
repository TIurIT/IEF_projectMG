package com.projectmg.Models;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Date;
import java.util.Objects;

@Table(name = "estoque")
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
    private Date dataDeCriacao;

    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;
        Material material = (Material) o;
        return Objects.equals(id, material.id) && Objects.equals(tipo, material.tipo)
                && Objects.equals(nome, material.nome) && Objects.equals(marca, material.marca)
                && Objects.equals(quantidade, material.quantidade)
                && Objects.equals(dataDeCriacao, material.dataDeCriacao);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, tipo, nome, marca, quantidade, dataDeCriacao);
    }
}
