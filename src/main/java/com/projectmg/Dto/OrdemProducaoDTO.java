package com.projectmg.Dto;

import com.projectmg.Models.Cliente;
import com.projectmg.Models.Material;
import com.projectmg.Models.Produto;

public class OrdemProducaoDTO {

    private Long id;
    private String gradeDescription;
    private Integer quantidade_total;
    private Produto produto;
    private Material material;
    private Cliente clienteId;

    public OrdemProducaoDTO(){}

    //ALTERAR VARIAVEIS -> ERROS DE SINTAXE

    public OrdemProducaoDTO(Long id,Cliente clienteId, String gradeDescription, Integer quantidade_total,
                            Produto produto, Material material) {
        this.id = id;
        this.clienteId = clienteId;
        this.gradeDescription = gradeDescription;
        this.quantidade_total = quantidade_total;
        this.produto = produto;
        this.material = material;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Cliente getClienteId() {
        return clienteId;
    }

    public void setClienteId(Cliente clienteId) {
        this.clienteId = clienteId;
    }

    public String getGradeDescription() {
        return gradeDescription;
    }

    public void setGradeDescription(String gradeDescription) {
        this.gradeDescription = gradeDescription;
    }

    public Integer getQuantidade_total() {
        return quantidade_total;
    }

    public void setQuantidade_total(Integer quantidade_total) {
        this.quantidade_total = quantidade_total;
    }

    public Produto getProduto() {
        return produto;
    }

    public void setProduto(Produto produto) {
        this.produto = produto;
    }

    public Material getMaterial() {
        return material;
    }

    public void setMaterial(Material material) {
        this.material = material;
    }
}
