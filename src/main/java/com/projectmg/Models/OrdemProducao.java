package com.projectmg.Models;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Table(name = "ordem_producao")
@Entity
@Getter
@Setter
public class OrdemProducao {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "produto_id")
    private Long produtoId;

    @Column(name = "quantidade")
    private Integer quantidade;


}
