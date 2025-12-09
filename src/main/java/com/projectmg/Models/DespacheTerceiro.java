package com.projectmg.Models;

import jakarta.persistence.*;
import com.projectmg.Enum.StatusDespache;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;

@Table(name = "tb_despache_terceiro")
@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class DespacheTerceiro {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    private Terceiro terceiro;

    @ManyToOne
    private Material material;

    private Double quantidadeEnviada;

    private Double quantidadeRetornada;

    @Enumerated(EnumType.STRING)
    private StatusDespache statusDespache;

    private LocalDateTime dataEnvio;

    private LocalDateTime dataRetorno;
}

