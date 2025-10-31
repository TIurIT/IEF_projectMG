package com.projectmg.Models;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.projectmg.Enum.TipoAcao;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Table(name = "tb_estoque_material")
@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Material {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String tipo;

    private String nome;

    private String fornecedor;

    // Agora é Double, representando KG
    private Double quantidade;

    // Novo campo: rendimento (quantas peças por kg, por exemplo)
    private Double rendimento;

    // Campo calculado: total de peças (rendimento * quantidade)
    @Column(name = "total_de_pecas")
    private Double totalDePecas;

    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private LocalDateTime dataDeCriacao;

    private LocalDateTime dataAtualizacao;

    private String usuarioUltimaAlteracao;

    private Double limiteMinimo;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private TipoAcao acao;

    private boolean ativo = true;

    @OneToMany(mappedBy = "material", cascade = CascadeType.ALL, orphanRemoval = true, fetch = FetchType.EAGER)
    @JsonIgnore
    private List<HistoricoMaterial> historicos = new ArrayList<>();

    @PrePersist
    public void prePersist() {
        this.dataDeCriacao = LocalDateTime.now();
        this.dataAtualizacao = LocalDateTime.now();
        this.acao = TipoAcao.CRIADO;

        if (this.rendimento != null && this.quantidade != null) {
            this.totalDePecas = this.rendimento * this.quantidade;
        }
    }

    @PreUpdate
    public void preUpdate() {
        this.dataAtualizacao = LocalDateTime.now();

        if (this.rendimento != null && this.quantidade != null) {
            this.totalDePecas = this.rendimento * this.quantidade;
        }
    }


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Material material)) return false;
        return ativo == material.ativo &&
                Objects.equals(id, material.id) &&
                Objects.equals(tipo, material.tipo) &&
                Objects.equals(nome, material.nome) &&
                Objects.equals(fornecedor, material.fornecedor) &&
                Objects.equals(quantidade, material.quantidade) &&
                Objects.equals(rendimento, material.rendimento) &&
                Objects.equals(totalDePecas, material.totalDePecas);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, tipo, nome, fornecedor, quantidade, rendimento, totalDePecas, ativo);
    }
}
