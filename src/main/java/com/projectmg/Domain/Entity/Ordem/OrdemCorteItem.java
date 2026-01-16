package com.projectmg.Domain.Entity.Ordem;

import com.projectmg.Domain.Entity.Material.Material;
import com.projectmg.Domain.Entity.Referencia;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.HashMap;
import java.util.Map;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class OrdemCorteItem {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(optional = false)
    private OrdemCorte ordemCorte;

    @ManyToOne(optional = false)
    private Referencia referencia;

    @ManyToOne(optional = false)
    private Material material;

    @ElementCollection
    @CollectionTable(
            name = "ordem_corte_grade",
            joinColumns = @JoinColumn(name = "ordem_corte_item_id")
    )
    @MapKeyColumn(name = "tamanho")
    @Column(name = "quantidade")
    private Map<String, Integer> gradeTamanhos = new HashMap<>();

    private Integer totalPecas;

    private Double materialEstimadoKg;
}
