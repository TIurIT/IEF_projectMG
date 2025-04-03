package com.projectmg.Models;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Objects;

@Table(name = "tb_ordem_producao_item")
@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class OrdemProducaoItem {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "grade_description")
    private String gradeDescription;

    @Column(name = "quantidade_total")
    private Integer quantidadeTotal;

    @ManyToOne
    @JoinColumn(name = "produto_id")
    private Produto produto;

    @ManyToOne
    @JoinColumn(name = "material_id")
    private Material material;

    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;
        OrdemProducaoItem that = (OrdemProducaoItem) o;
        return Objects.equals(id, that.id) && Objects.equals(gradeDescription, that.gradeDescription) && Objects.equals(quantidadeTotal, that.quantidadeTotal) && Objects.equals(produto, that.produto) && Objects.equals(material, that.material);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, gradeDescription, quantidadeTotal, produto, material);
    }
}
