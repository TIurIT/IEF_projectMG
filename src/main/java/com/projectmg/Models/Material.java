package com.projectmg.Models;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.projectmg.Enum.TipoAcao;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;
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

    @Column(name = "tipo")
    private String tipo;

    @Column(name = "nome")
    private String nome;

    @Column(name = "fornecedor")
    private String fornecedor;

    @Column(name = "quantidade")
    private Integer quantidade;

    @Column(name = "data_de_criacao")
    @JsonFormat(pattern = "yyyy-MM-dd")
    private LocalDateTime dataDeCriacao;

    @Column(name = "data_atualizacao")
    private LocalDateTime dataAtualizacao;

    @Column(name = "usuario_ultima_alteracao")
    private String usuarioUltimaAlteracao;

    @Column(name = "limite_Minimo", nullable = true)
    private Integer limiteMinimo;

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
    }

    @PreUpdate
    public void preUpdate() {
        this.dataAtualizacao = LocalDateTime.now();
    }

    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;
        Material material = (Material) o;
        return ativo == material.ativo && Objects.equals(id, material.id) && Objects.equals(tipo, material.tipo) && Objects.equals(nome, material.nome) && Objects.equals(fornecedor, material.fornecedor) && Objects.equals(quantidade, material.quantidade) && Objects.equals(dataDeCriacao, material.dataDeCriacao) && Objects.equals(dataAtualizacao, material.dataAtualizacao) && Objects.equals(usuarioUltimaAlteracao, material.usuarioUltimaAlteracao) && Objects.equals(limiteMinimo, material.limiteMinimo) && acao == material.acao && Objects.equals(historicos, material.historicos);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, tipo, nome, fornecedor, quantidade, dataDeCriacao, dataAtualizacao, usuarioUltimaAlteracao, limiteMinimo, acao, ativo, historicos);
    }
}
