package DTO;

import java.util.Date;
import java.util.UUID;

public class EstoqueDto {
    private UUID id;
    private String tipo;
    private String nome;
    private Integer quantidade;
    private String marca;
    private Date dataDeCriacao;

    public EstoqueDto(){}

    public UUID getId() {
        return id;
    }
    public void setId(UUID id) {
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
    public Integer getQuantidade() {
        return quantidade;
    }
    public void setQuantidade(Integer quantidade) {
        this.quantidade = quantidade;
    }
    public String getMarca() {
        return marca;
    }
    public void setMarca(String marca) {
        this.marca = marca;
    }
    public Date getDataDeCriacao() {
        return dataDeCriacao;
    }
    public void setDataDeCriacao(Date dataDeCriacao) {
        this.dataDeCriacao = dataDeCriacao;
    }
}
