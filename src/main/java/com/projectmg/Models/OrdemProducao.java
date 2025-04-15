package com.projectmg.Models;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Date;
import java.util.List;
import java.util.Objects;

@Entity
@Table(name = "tb_ordem_producao")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class OrdemProducao {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name="data_de_criacao")
    private Date dataDeCriacao;

    @ManyToOne
    @JoinColumn(name="cliente_id")
    private Cliente cliente;

    @OneToMany
    @JoinColumn(name = "ordem_producao_item_id", nullable = true)
    private List<OrdemProducaoItem> itens;

    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;
        OrdemProducao that = (OrdemProducao) o;
        return Objects.equals(id, that.id) && Objects.equals(dataDeCriacao, that.dataDeCriacao)
                && Objects.equals(cliente, that.cliente) && Objects.equals(itens, that.itens);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, dataDeCriacao, cliente, itens);
    }
}
