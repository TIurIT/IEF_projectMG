package com.projectmg.Models;


import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Objects;


@Table(name = "costureira")
@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Costureira {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "nome")
    private String nome;

    @Column(name = "bairro")
    private String bairro;

    @Column(name = "telefone", nullable = false)
    private String telefone;

    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;
            Costureira costureira = (Costureira) o;
        return Objects.equals(id, costureira.id);
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(id);
    }
}
