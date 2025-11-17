package com.projectmg.Dtos;

import com.projectmg.Models.Material;
import com.projectmg.Models.Referencia;
import lombok.Data;

@Data
public class OrdemProducaoItemDTO {

    private Long id;
    private String gradeDescription;
    private Integer quantidadeTotal;
    private Referencia referencia;
    private Material material;

    public OrdemProducaoItemDTO(){}

    public OrdemProducaoItemDTO(Long id, String gradeDescription, Integer quantidadeTotal,
                                Referencia referencia, Material material) {
        this.id = id;
        this.gradeDescription = gradeDescription;
        this.quantidadeTotal = quantidadeTotal;
        this.referencia = referencia;
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

    public Integer getQuantidadeTotal() {
        return quantidadeTotal;
    }

    public void setQuantidadeTotal(Integer quantidade_total) {
        this.quantidadeTotal = quantidadeTotal;
    }

    public Referencia getReferencia() {
        return referencia;
    }

    public void setReferencia(Referencia referencia) {
        this.referencia = referencia;
    }

    public Material getMaterial() {
        return material;
    }

    public void setMaterial(Material material) {
        this.material = material;
    }
}
