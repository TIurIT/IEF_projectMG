package com.projectmg.Models;

import com.projectmg.Enum.Servico;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Objects;

@Table(name = "terceiros")
@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Terceiros {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    
    @Column(name = "nome")
    private String nome;
    
    @Column(name = "bairro")
    private String bairro;
    
    @Column(name = "telefone", nullable = false)
    private String telefone;
    
    @Enumerated(EnumType.STRING)
    private Servico servico;

    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;
        Terceiros terceiros = (Terceiros) o;
        return Objects.equals(id, terceiros.id) && Objects.equals(nome, terceiros.nome) && Objects.equals(bairro, terceiros.bairro) && Objects.equals(telefone, terceiros.telefone) && servico == terceiros.servico;
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, nome, bairro, telefone, servico);
    }
}
