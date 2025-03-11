package com.projectmg.Models;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Objects;


@Table(name = "estamparia")
@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Estamparia {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "nome")
    private String nome;

    @Column(name = "bairro")
    private String bairro;

    @Column(name = "telefone")
    private String telefone;

    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;
        Estamparia estamparia = (Estamparia) o;
        return Objects.equals(id, estamparia.id);
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(id);
    }
}
