package com.projectmg.Dto;

public class OrdemProducaoDTO {

    private Long id;
    private String gradeDescription;
    private Integer quantidade_total;
    private ProdutoDTO produto;
    private MaterialDTO material;

    public OrdemProducaoDTO(){}

    public OrdemProducaoDTO(Long id, String gradeDescription, Integer quantidade_total, ProdutoDTO produto, MaterialDTO material) {
        this.id = id;
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

    public ProdutoDTO getProduto() {
        return produto;
    }

    public void setProduto(ProdutoDTO produto) {
        this.produto = produto;
    }

    public MaterialDTO getMaterial() {
        return material;
    }

    public void setMaterial(MaterialDTO material) {
        this.material = material;
    }
}
