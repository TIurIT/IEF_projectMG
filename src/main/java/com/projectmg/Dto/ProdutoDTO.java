package com.projectmg.Dto;

import lombok.Data;

@Data
public class ProdutoDTO {

    private Long id;
    private String nome;
    private String referencia;

    public ProdutoDTO(){}

    public ProdutoDTO(Long id, String nome, String referencia) {
        this.id = id;
        this.nome = nome;
        this.referencia = referencia;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getReferencia() {
        return referencia;
    }

    public void setReferencia(String referencia) {
        this.referencia = referencia;
    }
}
