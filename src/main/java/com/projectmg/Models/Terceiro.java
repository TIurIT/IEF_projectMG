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
public class Terceiro {
    
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
        Terceiro terceiro = (Terceiro) o;
        return Objects.equals(id, terceiro.id) && Objects.equals(nome, terceiro.nome) && Objects.equals(bairro, terceiro.bairro) && Objects.equals(telefone, terceiro.telefone) && servico == terceiro.servico;
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, nome, bairro, telefone, servico);
    }
}
