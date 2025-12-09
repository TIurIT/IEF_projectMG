package com.projectmg.Models;

import com.projectmg.Enum.TipoServico;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Objects;

@Table(name = "tb_terceiro")
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
    @Column(name = "tipo_Servico", nullable = false)
    private TipoServico tipoServico;

    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;
        Terceiro terceiro = (Terceiro) o;
        return Objects.equals(id, terceiro.id) && Objects.equals(nome, terceiro.nome) && Objects.equals(bairro, terceiro.bairro) && Objects.equals(telefone, terceiro.telefone) && tipoServico == terceiro.tipoServico;
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, nome, bairro, telefone, tipoServico);
    }
}
