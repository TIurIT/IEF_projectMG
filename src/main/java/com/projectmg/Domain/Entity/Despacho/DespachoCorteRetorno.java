package com.projectmg.Domain.Entity.Despacho;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;

@Entity
@Table(name = "despacho_corte_retorno")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class DespachoCorteRetorno {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @OneToOne
    @JoinColumn(name = "despacho_id", nullable = false, unique = true)
    private DespachoCorte despacho;

    @Column(nullable = false)
    private Double quantidadeMaterialRetornado;

    @Column(nullable = false)
    private Double divergenciaMaterial;

    private LocalDateTime dataRetorno;

    private String usuario;
}
