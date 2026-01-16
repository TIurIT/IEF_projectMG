package com.projectmg.Domain.Entity.Ordem;

import com.projectmg.Domain.Enum.StatusOrdemCorte;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.List;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class OrdemCorte {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String usuarioCriador;

    private LocalDateTime dataCriacao;

    @Enumerated(EnumType.STRING)
    private StatusOrdemCorte status;

    @OneToMany(
            mappedBy = "ordemCorte",
            cascade = CascadeType.ALL,
            orphanRemoval = true
    )
    private List<OrdemCorteItem> itens;

    @PrePersist
    public void prePersist() {
        this.dataCriacao = LocalDateTime.now();
        this.status = StatusOrdemCorte.CRIADA;
    }
}
