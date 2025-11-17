package com.projectmg.Models;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Objects;

@Table(name = "tb_valor_servico")
@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class ValorServico {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @OneToOne
    @JoinColumn(name = "terceiro_id", nullable = false)
    private Terceiro terceiro;

    @OneToOne
    @JoinColumn(name = "produto_id", nullable = false)
    private Referencia referencia;

    @Column(nullable = false)
    private Double valor;

    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;
        ValorServico that = (ValorServico) o;
        return id == that.id && Objects.equals(terceiro, that.terceiro)
                && Objects.equals(referencia, that.referencia) && Objects.equals(valor, that.valor);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, terceiro, referencia, valor);
    }
}
