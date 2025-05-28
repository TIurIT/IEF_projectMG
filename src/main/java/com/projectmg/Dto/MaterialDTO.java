package com.projectmg.Dto;

import com.projectmg.Security.UsuarioAuditoria;
import jakarta.persistence.Column;
import jakarta.persistence.PrePersist;
import jakarta.persistence.PreUpdate;
import lombok.Data;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Date;

@Data
public class MaterialDTO {
    private Long id;
    private String tipo;
    private String nome;
    private String marca;
    private Integer quantidade;
    private LocalDate dataDeCriacao;
    private LocalDateTime dataAtualizacao;
    private String usuarioUltimaAlteracao;

    @PrePersist
    @PreUpdate
    public void atualizarDataAtualizacao() {
        this.dataAtualizacao = LocalDateTime.now();
        this.usuarioUltimaAlteracao = UsuarioAuditoria.getUsuarioLogado();
    }

    public MaterialDTO(Long id, String tipo, String nome, String marca, Integer quantidade, LocalDate dataDeCriacao, LocalDateTime dataAtualizacao, String usuarioUltimaAlteracao) {
        this.id = id;
        this.tipo = tipo;
        this.nome = nome;
        this.marca = marca;
        this.quantidade = quantidade;
        this.dataDeCriacao = dataDeCriacao;
        this.dataAtualizacao = dataAtualizacao;
        this.usuarioUltimaAlteracao = usuarioUltimaAlteracao;
    }

    public MaterialDTO(){}

    public Long getId() {
        return id;
    }
    public void setId(Long id) {
        this.id = id;
    }
    public String getTipo() {
        return tipo;
    }
    public void setTipo(String tipo) {
        this.tipo = tipo;
    }
    public String getNome() {
        return nome;
    }
    public void setNome(String nome) {
        this.nome = nome;
    }
    public String getMarca() {
        return marca;
    }
    public void setMarca(String marca) {
        this.marca = marca;
    }
    public Integer getQuantidade() {
        return quantidade;
    }
    public void setQuantidade(Integer quantidade) {
        this.quantidade = quantidade;
    }
    public LocalDate getDataDeCriacao() {
        return dataDeCriacao;
    }
    public void setDataDeCriacao(LocalDate dataDeCriacao) {
        this.dataDeCriacao = dataDeCriacao;
    }
    public LocalDateTime getDataAtualizacao() {
        return dataAtualizacao;
    }
    public void setDataAtualizacao(LocalDateTime dataAtualizacao) {
        this.dataAtualizacao = dataAtualizacao;
    }
    public String getUsuarioUltimaAlteracao() {
        return usuarioUltimaAlteracao;
    }
    public void setUsuarioUltimaAlteracao(String usuarioUltimaAlteracao) {
        this.usuarioUltimaAlteracao = usuarioUltimaAlteracao;
    }
}
