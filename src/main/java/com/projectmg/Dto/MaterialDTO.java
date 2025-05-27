package com.projectmg.Dto;

import lombok.Data;

import java.time.LocalDate;
import java.util.Date;

@Data
public class MaterialDTO {
    private Long id;
    private String tipo;
    private String nome;
    private String marca;
    private Integer quantidade;
    private LocalDate dataDeCriacao;

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
}
